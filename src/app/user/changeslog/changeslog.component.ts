import { Component, OnInit } from '@angular/core';
import { ChangesService } from 'src/service/changes.service'
import { ChangesDTO } from 'src/dto/changesdto';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-changeslog',
  templateUrl: './changeslog.component.html',
  styleUrls: ['./changeslog.component.css']
})
export class ChangeslogComponent implements OnInit {

  changes: ChangesDTO[]

  constructor(private service: ChangesService) { }

  ngOnInit() {
    this.getAll()
  }

  getAll(): void {
    this.service.getAllByUser((JSON.parse(localStorage.getItem('currentUser')) as UserDTO).id).subscribe(changes => this.changes = changes)
  }

}
