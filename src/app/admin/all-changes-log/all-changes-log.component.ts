import { Component, OnInit } from '@angular/core';
import { ChangesDTO } from 'src/app/dto/changes-dto';
import { ChangesService } from 'src/app/service/changes.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-all-changes-log',
  templateUrl: './all-changes-log.component.html',
  styleUrls: ['./all-changes-log.component.css']
})
export class AllChangesLogComponent implements OnInit {

  changesDTO: ChangesDTO[]

  constructor(private service: ChangesService, private router: Router) { }

  ngOnInit() {
    this.service.getAll().subscribe(changes => this.changesDTO = changes.map(el=>{
      if(!el.tag_position){
        el.tag_position = ''
      }
      if(!el.removed_tag){
        el.removed_tag = ''
      }
      return el
    }))
  }

  goToChanges(id: number) {
    this.router.navigate([`${(JSON.parse(localStorage.getItem('user')) as UserDTO).authorities.includes('ROLE_ADMIN') ? 'admin' : 'user'}-dashboard/changes`],
      { queryParams: { id: id } })
  }

}
