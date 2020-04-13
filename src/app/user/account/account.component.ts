import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: UserDTO
  usertoinsert: UserDTO = new UserDTO()

  constructor(private service: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  update(user: UserDTO): void {
    this.service.update(user).subscribe(()=>{
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.user = user
    })
  }

}
