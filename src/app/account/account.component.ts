import { Component, OnInit } from '@angular/core';
import { UserDto } from '../dto/user-dto';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: UserDto

  constructor(private service: UserService) { }

  ngOnInit() {
    if(new URLSearchParams(window.location.search).get('id')){
      this.restoreAccount()
    }else {
    this.user = JSON.parse(localStorage.getItem('user'))
  }
}

  saveChanges(){
    this.service.update(this.user).subscribe(user=>{localStorage.setItem('user', JSON.stringify(user))})
  }

  restoreAccount (){
    this.service.read(parseInt(new URLSearchParams(window.location.search).get('id'))).subscribe(user=>{
      this.user = user})
    }
}
