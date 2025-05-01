import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {BookComponent} from './book/book.component';
import {Book} from './models/book';
import {BookListComponent} from './book-list/book-list.component';
import {HeaderComponent} from './header/header.component';

@Component({
  selector: 'app-root',
  imports: [
    BookComponent,
    BookListComponent,
    HeaderComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {
}
