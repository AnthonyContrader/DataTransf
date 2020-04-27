import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/dto/user-dto';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserDTO[]

  constructor(private service: UserService) { }

  ngOnInit() {
    this.service.getAll().subscribe(users => this.users = users)
  }

  goToAccount(login: string) {

  }

  deleteUser(login: string) {
    this.service.delete(login).subscribe(() => {
      this.service.getAll().subscribe(users => this.users = users)
    })
  }

}
