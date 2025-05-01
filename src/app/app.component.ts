import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {BookComponent} from './book/book.component';
import {Book} from './models/book';

@Component({
  selector: 'app-root',
  imports: [
    BookComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  myBook!: Book;

  ngOnInit() {
    this.myBook = new Book
    (
      'Silo',
      'Hugh Howey',
      "Actes Sud",
      '2013',
      'silo.jpg',
      "Dans un futur indéterminé, des survivants vivent depuis plusieurs générations dans un immense silo creusé dans la terre, à l'abri d'une atmosphère devenue toxique.",
      new Date(),
      5
    );
  }
}
