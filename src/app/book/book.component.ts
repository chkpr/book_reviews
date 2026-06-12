import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../models/book';
import {DatePipe} from '@angular/common';
import {BooksService} from '../services/books.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-book',
  imports: [
    DatePipe
  ],
  templateUrl: './book.component.html',
  styleUrl: './book.component.scss'
})
export class BookComponent {
  @Input() book!: Book;

  constructor(private booksService: BooksService,
              private router: Router) {}

  onViewBook(){
    this.router.navigateByUrl(`books/${this.book.id}`);
  }
}
