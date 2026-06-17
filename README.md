# BookReviews

A full stack multiuser app built with Angular and Spring Boot to discover books and share reviews. Originally a book presentation app built with Angular 19, developed as a personal project to practice Angular after completing OpenClassrooms Angular courses. Taking it further with new functionalities to continue practicing and learning.

## Tech stack

- Angular 19
- TypeScript
- SCSS
- Java 21
- Spring Boot 4
- Gradle
- PostgreSQL

## Prerequisites

- Node.js & npm
- Java 21
- PostgreSQL
- `application.properties` file in `backend/src/main/resources/` (see `application.properties.example`)

## Local setup

### 1 - Database

Create a PostgreSQL database:

```sql
CREATE DATABASE bookreview;
```

### 2 - Backend (Spring Boot API)

```bash
cd backend
./gradlew bootRun
```

### 3 - Frontend (Angular)

```bash
npm install
ng serve
```

Open your browser at `http://localhost:4200`

## Learning resources

- [Débutez avec Angular](https://openclassrooms.com/fr/courses/7471261-debutez-avec-angular)
- [Perfectionnez-vous sur Angular](https://openclassrooms.com/fr/courses/7471281-perfectionnez-vous-sur-angular)
- [Building a REST API with Spring Boot](https://spring.academy/courses/building-a-rest-api-with-spring-boot)
- [Sécurisez votre application web avec Spring Security](http://openclassrooms.com/fr/courses/7137776-securisez-votre-application-web-avec-spring-security)

## Notes

This project was developed as a learning exercise and is not intended for production use.
