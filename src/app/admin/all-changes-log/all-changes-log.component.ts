import { Component, OnInit } from '@angular/core';
import { ChangesService } from 'src/app/service/changes.service';
import { ChangesDto } from 'src/app/dto/changes-dto';
import { UserDto } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-all-changes-log',
  templateUrl: './all-changes-log.component.html',
  styleUrls: ['./all-changes-log.component.css']
})
export class AllChangesLogComponent implements OnInit {

  all_changes: ChangesDto[]

  constructor(private service: ChangesService) { }

  ngOnInit() {
    this.service.getAll().subscribe(all_changes => {this.all_changes = all_changes; console.log(all_changes)})
    
  }

  goToChanges(id: number){
    window.location.href = 
    `admin-dashboard/newchanges?id=${id}`
  }

}
