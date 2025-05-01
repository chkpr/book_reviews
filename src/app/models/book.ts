import {LikeType} from './like-type.type';

export class Book {

  id: string;

  constructor(
    public title: string,
    public author: string,
    public publisher: string,
    public publishing: string,
    public image: string,
    public description: string,
    public createdAt: Date,
    public likes: number,
    public review: string
  ){
    this.id = crypto.randomUUID().substring(0, 8);
  }

  addLike(): void {
    this.likes++;
  }

  removeLike(): void {
    this.likes--;
  }

  like(likeType: LikeType): void {
    if (likeType === 'like') {
      this.addLike()
    } else if (likeType === 'unlike') {
      this.removeLike();
    }
  }
}
