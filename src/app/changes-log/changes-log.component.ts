import { Component, OnInit } from '@angular/core';
import { ChangesDto } from '../dto/changes-dto';
import { ChangesService } from '../service/changes.service';
import { UserDto } from '../dto/user-dto';

@Component({
  selector: 'app-changes-log',
  templateUrl: './changes-log.component.html',
  styleUrls: ['./changes-log.component.css']
})
export class ChangesLogComponent implements OnInit {

  user_changes: ChangesDto[]

  constructor(private service: ChangesService) { }

  ngOnInit() {
    this.service.getAllByUser((JSON.parse(localStorage.getItem('user')) as UserDto).id)
    .subscribe(user_changes => this.user_changes = user_changes.filter(changes=>{
      if(changes.name){
        return true
      }
    }))
  }

  goToChanges(id: number){
    window.location.href = 
    `${(JSON.parse(localStorage.getItem('user')) as UserDto).usertype.toString().toLowerCase()}-dashboard/newchanges?id=${id}`
  }

}
