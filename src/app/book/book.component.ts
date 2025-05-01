import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../models/book';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-book',
  imports: [
    DatePipe
  ],
  templateUrl: './book.component.html',
  styleUrl: './book.component.scss'
})
export class BookComponent implements OnInit {
  @Input() book!: Book;

  likes!: number;
  userHasLiked!: boolean;

  ngOnInit() {
    this.likes = 5;
    this.userHasLiked = false;
  }

  onLike() {
    if(this.userHasLiked){
      this.unLike();
    } else {
      this.like();
    }
  }

  unLike() {
    this.book.removeLike();
    this.userHasLiked = false;
  }

  like(): void {
    this.book.addLike();
    this.userHasLiked = true;
  }
}
