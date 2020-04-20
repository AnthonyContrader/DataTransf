import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  user : UserDto

  constructor() { }

  ngOnInit() {
    this.user = (JSON.parse(localStorage.getItem('user')) )
  }

}
