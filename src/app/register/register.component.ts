import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  email='';
  password='';
  password_confirmation='';
  username='';
  name='';
  firstname='';
  birthDate='';
  errorMessage=''



  constructor(
    private authService: AuthService,
    private router : Router,
  ) {}

  onSubmit():void{
    if(this.password !== this.password_confirmation) {
      this.errorMessage = 'Les mots de passe ne correspondent pas';
      return;
    }
  this.authService.register({
    email:this.email,
    password:this.password,
    username:this.username,
    name:this.name,
    firstname:this.firstname,
    birthDate:this.birthDate
  }).subscribe({
    next: () => {
      this.router.navigate(['/books']);
    },
    error: (err: any) => {
      if (err.error && err.error.password) {
        this.errorMessage = err.error.password;
      } else {
        this.errorMessage = 'Une erreur est survenue lors de l\'inscription';
      }
      console.error(err);
    }
  })
  }
  }

