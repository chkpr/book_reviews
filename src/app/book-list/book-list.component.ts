import {Component, OnInit} from '@angular/core';
import {Book} from '../models/book';
import {BookComponent} from '../book/book.component';
import {BooksService} from '../services/books.service';

@Component({
  selector: 'app-book-list',
  imports: [
    BookComponent
  ],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {
  books!: Book[];

  constructor(private booksService: BooksService) {
  }

    ngOnInit() {
      this.books = this.booksService.getBooks();
    }
  }


