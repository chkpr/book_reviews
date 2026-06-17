import {Component, OnInit} from '@angular/core';
import {Book} from '../models/book';
import {BooksService} from '../services/books.service';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {Review} from '../models/review';
import {ReviewService} from '../services/review.service';

@Component({
  selector: 'app-single-book',
  imports: [    RouterLink  ],
  templateUrl: './single-book.component.html',
  styleUrl: './single-book.component.scss'
})
export class SingleBookComponent implements OnInit {
  book?: Book;
  reviews: Review[] = [];

  constructor(private booksService: BooksService,
              private reviewService: ReviewService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const bookId = Number(this.route.snapshot.params['id']);
    this.getBook(bookId);
    this.getReviews(bookId);
  }

  private getBook(bookId: number) {
    this.booksService.getBookById(bookId).subscribe({
      next: (book : Book) => this.book = book,
      error: (err: any) => console.error('Erreur lors du chargement du livre', err)
    });
  }


private getReviews(bookId: number) {
  this.reviewService.getReviewsByBookId(bookId).subscribe({
    next: reviews => this.reviews = reviews,
    error: err => console.error('Erreur lors du livre', err)
  });
}
}
