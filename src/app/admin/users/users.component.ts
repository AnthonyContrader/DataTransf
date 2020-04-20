import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { UserDto } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users : UserDto[]

  constructor(private service : UserService) { }

  ngOnInit() {
    this.service.getAll().subscribe( users => this.users = users)
  }

  deleteUser(user : UserDto) {
    this.service.delete(user.id).subscribe( users => this.service.getAll().subscribe( users => this.users = users));
  }
  goToAccount(id: number){
   window.location.href = `admin-dashboard/account?id=${id}`
  }

}
