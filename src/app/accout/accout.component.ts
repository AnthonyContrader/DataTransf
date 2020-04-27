import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { UserDTO } from '../dto/user-dto';

@Component({
  selector: 'app-accout',
  templateUrl: './accout.component.html',
  styleUrls: ['./accout.component.css']
})
export class AccoutComponent implements OnInit {

  userDTO: UserDTO

  constructor(private service: UserService) { }

  ngOnInit() {
    this.userDTO = JSON.parse(localStorage.getItem('user'))
  }

  saveChanges() {
    this.service.update(this.userDTO).subscribe(user => {
      this.userDTO = user
      localStorage.setItem('user', JSON.stringify(user))
    })
  }

}
