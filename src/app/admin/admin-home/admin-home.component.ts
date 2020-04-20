import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  user : UserDto

  constructor() { }

  ngOnInit() {
    this.user = (JSON.parse(localStorage.getItem('user')) )
  }

}
