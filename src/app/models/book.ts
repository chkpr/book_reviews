export class Book {
  constructor(
    public title: string,
    public author: string,
    public publisher: string,
    public publishing: string,
    public image: string,
    public description: string,
    public createdAt: Date,
    public likes: number,
  ){}

  addLike(): void {
    this.likes++;
  }

  removeLike(): void {
    this.likes--;
  }
}
