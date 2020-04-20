import { Component, OnInit } from '@angular/core';
import { LoginDto } from '../dto/login-dto';
import { UserService } from '../service/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDto } from '../dto/user-dto';
import { UserType } from '../dto/user-type.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDto

  signup = false

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

  signUp(signupForm: NgForm){

    let userDto = new UserDto()

    userDto.username = signupForm.value.username

    userDto.password = signupForm.value.password

    userDto.usertype = UserType.USER

    this.service.insert(userDto).subscribe(user=> {
      this.service.login(user).subscribe(user=>{
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
    })
  }

  changeForm(){
    this.signup = !this.signup
  }

}
