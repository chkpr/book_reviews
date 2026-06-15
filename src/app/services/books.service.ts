import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {Book} from '../models/book';

@Injectable({
  providedIn: 'root'
  })
  export class BooksService {
    private apiUrl = 'http://localhost:8080/api/books';

    constructor(private http: HttpClient) { }

    getBooks(): Observable<Book[]> {
      return this.http.get<Book[]>(this.apiUrl);
    }

    getBooksById(id: number): Observable<Book> {
      return this.http.get<Book>(`${this.apiUrl}/${id}`);
    }
  }

