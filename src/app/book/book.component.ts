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
export class BookComponent implements OnInit {
  @Input() book!: Book;

  likes!: number;
  userHasLiked!: boolean;

  constructor(private booksService: BooksService,
              private router: Router) {}

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
    this.booksService.likeBookById(this.book.id, 'unlike');
    this.userHasLiked = false;
  }

  like(): void {
    this.booksService.likeBookById(this.book.id, 'like');
    this.userHasLiked = true;
  }

  onViewBook(){
    this.router.navigateByUrl(`books/${this.book.id}`);
  }
}
