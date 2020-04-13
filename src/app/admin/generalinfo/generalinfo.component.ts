import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-generalinfo',
  templateUrl: './generalinfo.component.html',
  styleUrls: ['./generalinfo.component.css']
})
export class GeneralinfoComponent implements OnInit {

  user: UserDTO

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'))
  }

}
