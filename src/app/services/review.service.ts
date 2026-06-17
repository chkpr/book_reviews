import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Review} from '../models/review';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  getReviewsByBookId(bookId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/books/${bookId}/reviews`);
  }
}
