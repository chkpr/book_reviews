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
  book?: Book;

  constructor(private booksService: BooksService,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.getBook();
  }

  private getBook() {
    const bookId = this.route.snapshot.params['id'];
    this.booksService.getBooksById(bookId).subscribe({
      next: book => {
        this.book = book;
      },
      error: (err) => {
        console.error('Erreur lors du chargement du livre', err);
      }
    });
  }
}
