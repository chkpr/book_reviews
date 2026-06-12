import { Author } from './author';
import { Category } from './category';

export interface Book {
  id: number;
  title: string;
  isbn: string;
  summary: string;
  publishedYear:number;
  coverUrl: string | null;
  author: Author;
  categories: Category[];


}
