import { Routes } from '@angular/router';
import {BookListComponent} from './book-list/book-list.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {SingleBookComponent} from './single-book/single-book.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {authGuard} from './auth.guard';

export const routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'books', component: BookListComponent, canActivate: [authGuard] },
  { path: 'books/:id', component: SingleBookComponent, canActivate: [authGuard] },
];
