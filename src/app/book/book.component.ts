import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-book',
  imports: [],
  templateUrl: './book.component.html',
  styleUrl: './book.component.scss'
})
export class BookComponent implements OnInit {
  title!: string;
  author!: string;
  publisher!: string;
  publishing!: string;
  image!: string;
  description!: string;
  createdAt!: Date;
  likes!: number;
  userHasLiked!: boolean;

  ngOnInit() {
    this.title = 'Silo';
    this.author = 'Hugh Howey';
    this.publisher = 'Actes Sud';
    this.publishing = '2013'
    this.image = 'silo.jpg'
    this.description = '"Dans un futur indéterminé, des survivants vivent depuis plusieurs générations dans un immense silo creusé dans la terre, à l\'abri d\'une atmosphère devenue toxique."';
    this.createdAt = new Date();
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
    this.likes--;
    this.userHasLiked = false;
  }

  like(): void {
    this.likes++;
    this.userHasLiked = true;
  }
}
