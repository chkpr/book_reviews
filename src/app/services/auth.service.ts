import {Injectable} from '@angular/core';
import {Observable, tap} from 'rxjs';
import {HttpClient} from '@angular/common/http';

interface AuthResponse {
  token: string;
}

interface RegisterRequest {
  email: string;
  password: string;
  username: string;
  name: string;
  firstname:string;
  birthDate: string;
}

interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn:'root'
})

export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data).pipe(
      tap(response => this.storeToken(response.token))
    );
  }

  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data).pipe(
      tap(response => this.storeToken(response.token))
    );
  }

  logout(): void{
  localStorage.removeItem('token');
  }

  isLoggedIn():boolean {
  return !!localStorage.getItem('token');
  }

  private storeToken(token: string): void {
  localStorage.setItem('token', token);
  }
}



