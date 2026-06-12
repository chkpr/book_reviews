import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email='';
  password=''
  errorMessage=''

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit():void{
    this.authService.login({email:this.email,password:this.password}).subscribe({
      next:()=>{
        this.router.navigate(['/books']);
      },
      error:(err: any)=>{
        this.errorMessage = 'Email ou mot de passe incorrect';
        console.error(err);
      }
    })
  }
}
