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
    this.user = JSON.parse(localStorage.getItem('user'))
  }

  saveChanges(){
    this.service.update(this.user).subscribe(user=>{localStorage.setItem('user', JSON.stringify(user))})
  }

}
