import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: UserDTO

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'))
  }

  

}
