import {Component, OnInit} from '@angular/core';
import {Book} from '../models/book';
import {DatePipe} from '@angular/common';
import {BooksService} from '../services/books.service';
import {ActivatedRoute, RouterLink} from '@angular/router';

@Component({
  selector: 'app-single-book',
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './single-book.component.html',
  styleUrl: './single-book.component.scss'
})
export class SingleBookComponent implements OnInit {
  book!: Book;
  likes!: number;
  userHasLiked!: boolean;

  constructor(private booksService: BooksService,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.prepareInterface();
    this.getBook();
  }

  onLike() {
    if(this.userHasLiked){
      this.unLike();
    } else {
      this.like();
    }
  }

  unLike() {
    this.booksService.likeBookById(this.book.id, 'unlike');
    this.userHasLiked = false;
  }

  like(): void {
    this.booksService.likeBookById(this.book.id, 'like');
    this.userHasLiked = true;
  }

  private prepareInterface() {
    this.userHasLiked = false;
  }

  private getBook() {
    const bookId = this.route.snapshot.params['id'];
    this.book = this. booksService.getBooksById(bookId);
  }
}
