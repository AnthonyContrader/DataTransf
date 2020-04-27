import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../service/user.service';
import { LoginDTO } from '../dto/login-dto';
import { Router } from '@angular/router';
import { SignupDTO } from '../dto/signup-dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  signup = false
  loginDTO: LoginDTO
  signupDTO: SignupDTO
  signupNotify = false

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {

  }

  signUp(signupForm: NgForm) {
    this.signupDTO = new SignupDTO(signupForm.value.login, signupForm.value.password, signupForm.value.email)
    this.service.signup(this.signupDTO).subscribe(() => {

      this.signupNotify = true

      setTimeout(() => {
        this.signup = !this.signup
        this.signupNotify = false
      }, 1000)

    })

  }

  changeForm() {
    this.signup = !this.signup
  }

  login(loginForm: NgForm) {
    this.loginDTO = new LoginDTO(loginForm.value.username, loginForm.value.password, loginForm.value.rememberMe ? true : false)

    this.service.login(this.loginDTO).subscribe(obj => {
      if (obj.id_token) {

        localStorage.setItem('id_token', obj.id_token)

        this.service.isLogged(this.loginDTO, obj.id_token).subscribe(user => {
          localStorage.setItem('user', JSON.stringify(user))
          if (user.authorities.includes('ROLE_ADMIN')) {
            this.router.navigate(['admin-dashboard'])
          } else {
            this.router.navigate(['user-dashboard'])
          }

        })
      }
    })
  }

}
