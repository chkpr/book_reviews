import { Injectable } from "@angular/core";
import {Book} from '../models/book';
import {LikeType} from '../models/like-type.type';

@Injectable({
  providedIn: 'root'
  })
  export class BooksService {
    private books: Book[] = [
      new Book(
        'Silo',
        'Hugh Howey',
        "Actes Sud",
        '2012',
        'silo.jpg',
        "Dans un futur indéterminé, des survivants vivent depuis plusieurs générations dans un immense silo creusé" +
        " dans la terre, à l’abri d’une atmosphère devenue toxique. Seul un écran relayant les images filmées par " +
        "des caméras les relie au monde extérieur. Lorsque cette société bannit l’un des siens, il est envoyé dehors, " +
        "vers une mort certaine, et pourtant, tous sans exception vont, avant de mourir, nettoyer les capteurs des caméras. " +
        "Pourquoi ? Une grande bouffée d’air (frais) dans la SF.",
        new Date(),
        5,
        '"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque ' +
        'laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto ' +
        'beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur ' +
        'aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi ' +
        'nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, ' +
        'adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam ' +
        'aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis ' +
        'suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit ' +
        'qui in ea voluptate velit esse quam nihil molestiae consequatur, ' +
        'vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"'
      ),
      new Book(
        'Silo: Origines',
        'Hugh Howey',
        "Actes Sud",
        '2013',
        'silo2.jpg',
        "En 2049, le monde est encore tel que nous le connaissons, mais le temps est compté. Seule une poignée de potentats savent ce que l'avenir réserve. Il s'y préparent. Ils essaient de nous en protéger. Ils vont nous engager sur une voie sans retour.",
        new Date(),
        5,
        '"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque ' +
        'laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto ' +
        'beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur ' +
        'aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi ' +
        'nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, ' +
        'adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam ' +
        'aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis ' +
        'suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit ' +
        'qui in ea voluptate velit esse quam nihil molestiae consequatur, ' +
        'vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"'
      ),
      new Book(
        'Nuit et jour',
        'Virginia Woolf',
        'Gallimard',
        '1919',
        'nuit_et_jour.jpg',
        '"Mêlant comédie de mœurs et satire de la société anglaise à la veille de la Grande Guerre, ce deuxième roman de Virginia Woolf, paru en 1919, raconte l’éducation sentimentale de jeunes gens qui doivent choisir entre une existence confortablement ancrée dans le passé et l\'aventure dans l’inconnu."',
        new Date(),
        5,
        '"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque ' +
        'laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto ' +
        'beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur ' +
        'aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi ' +
        'nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, ' +
        'adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam ' +
        'aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis ' +
        'suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit ' +
        'qui in ea voluptate velit esse quam nihil molestiae consequatur, ' +
        'vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"'
      ),
      new Book(
        'Vente à la criée du lot n°49',
        'Edmonde Charles-Roux',
        'Le livre de Poche',
        '1966',
        'oublier_palerme.webp',
        '"D’un côté, Palerme, la Sicile de la poussière, de l’étouffement, de l’honneur, de la misère, ' +
        'des passions gratuites et violentes, de la mer... De l’autre, n’importe laquelle de nos métropoles ' +
        'de commerce, d’argent, avec leur façon de briser les vies par la hâte, la férocité... ' +
        'Et, voguant entre ces deux univers, d’une époque à l’autre, les émigrants, paysans ou seigneurs, nostalgiques ou avides de recommencer"',
        new Date(),
        5,
        '"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque ' +
        'laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto ' +
        'beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur ' +
        'aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi ' +
        'nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, ' +
        'adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam ' +
        'aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis ' +
        'suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit ' +
        'qui in ea voluptate velit esse quam nihil molestiae consequatur, ' +
        'vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"'
      )

    ];
    getBooks(): Book[] {
      return [...this.books];
    }

    getBooksById(bookId:string): Book {
      const foundBook = this.books.find(book => book.id === bookId);
      if (!foundBook) {
        throw new Error('Book not found!');
      }
      return foundBook;
    }

  likeBookById(bookId: string, likeType: LikeType): void {
    const book = this.getBooksById(bookId);
    if (!book) {
      throw new Error('FaceSnap not found!');
    }
    book.like(likeType);
  }
  }

