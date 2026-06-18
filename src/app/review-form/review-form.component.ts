import { Component, Input } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ReviewService} from '../services/review.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './review-form.component.html',
  styleUrl: './review-form.component.scss'
})


export class ReviewFormComponent {
  @Input() bookId!:number;
  comment = '';
  rating = 0;
  errorMessage=''


constructor(
  private reviewService: ReviewService,
  private router : Router,
) {}

  isSubmitting = false;
  successMessage='';

  onSubmit():void {
    this.isSubmitting = true;
    this.reviewService.createReview(this.comment, this.rating, this.bookId).subscribe({
      next: () => {
        this.isSubmitting=false;
        this.successMessage='Votre commentaire a bien été envoyé';
        this.comment = '';
        this.rating=0;
        this.router.navigate(['/books', this.bookId]);
      },
      error: (err: any) => {
        this.isSubmitting = false;
        this.errorMessage = 'Erreur lors de l\'enregistrement du commentaire';
        console.error(err);
      }
    });
  }

}
