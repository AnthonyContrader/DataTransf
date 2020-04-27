import { Component, OnInit } from '@angular/core';
import { ChangesDTO } from '../dto/changes-dto';
import { ChangesService } from '../service/changes.service';
import { UserDTO } from '../dto/user-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changes-log',
  templateUrl: './changes-log.component.html',
  styleUrls: ['./changes-log.component.css']
})
export class ChangesLogComponent implements OnInit {

  changesDTO: ChangesDTO[]

  constructor(private service: ChangesService, private router: Router) { }

  ngOnInit() {
    this.service.readAllByUser((JSON.parse(localStorage.getItem('user')) as UserDTO).id)
      .subscribe(changes => this.changesDTO = changes.map(el => {
        if (!el.tag_position) {
          el.tag_position = ''
        }
        if (!el.removed_tag) {
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
