import { Component, OnInit } from '@angular/core';
import { LoginDto } from '../dto/login-dto';
import { UserService } from '../service/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDto

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
  }

  login(loginForm: NgForm){
    this.loginDTO = new LoginDto(loginForm.value.username, loginForm.value.password, loginForm.value.rememberMe? true: false)

    this.service.login(this.loginDTO).subscribe(user=>{
      if(user){
        localStorage.setItem('user', JSON.stringify(user))
        switch (user.usertype.toString()) {
          
          case 'ADMIN': 
            this.router.navigate(['admin-dashboard'])
            break;
      
          case 'USER':
            this.router.navigate(['user-dashboard'])
            break;
          
        }
      }     
    })
  }

  signUp(signUpForm: NgForm){
    
  }
}
