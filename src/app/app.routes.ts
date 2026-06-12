import { Routes } from '@angular/router';
import {BookListComponent} from './book-list/book-list.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {SingleBookComponent} from './single-book/single-book.component';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  {path:'books/:id', component: SingleBookComponent},
  { path: 'books', component: BookListComponent },
  { path: '', component: LandingPageComponent},
  { path: 'login', component: LoginComponent }
];
