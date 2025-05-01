import { Routes } from '@angular/router';
import {BookListComponent} from './book-list/book-list.component';
import {LandingPageComponent} from './landing-page/landing-page.component';

export const routes: Routes = [
  { path: 'books', component: BookListComponent },
  { path: '', component: LandingPageComponent}
];
