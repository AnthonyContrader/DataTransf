import { Component, OnInit } from '@angular/core';
import { ChangesService } from '../service/changes.service';
import { ActivatedRoute } from '@angular/router';
import { ChangesDTO } from '../dto/changes-dto';
import { UserDTO } from '../dto/user-dto';

@Component({
  selector: 'app-new-changes',
  templateUrl: './new-changes.component.html',
  styleUrls: ['./new-changes.component.css']
})
export class NewChangesComponent implements OnInit {

  changesDTO = new ChangesDTO()

  tagQuantity = new Array()
  removedTagQuantity = new Array()
  original_tag = new Array<string>()
  new_tag = new Array<string>()
  removed_tag = new Array<string>()

  constructor(private service: ChangesService, private router: ActivatedRoute) { }

  ngOnInit() {
    this.router.queryParams.subscribe(param => {
      if (param.id) {
        this.service.read(param.id).subscribe(changes => {
          this.changesDTO = changes
          this.restoreChanges()
        })
      }
      this.changesDTO.user = (JSON.parse(localStorage.getItem('user')) as UserDTO).id
    })
  }

  restoreChanges() {
    this.tagQuantity = new Array()
    this.removedTagQuantity = new Array()
    this.original_tag = new Array<string>()
    this.new_tag = new Array<string>()
    this.removed_tag = new Array<string>()

    if (this.changesDTO.tag_name) {
      this.changesDTO.tag_name.replace('[', '').replace(']', '').split(',').forEach(tag_and_new_tag => {
        this.original_tag.push(tag_and_new_tag.split('=')[0].replace(' ', ''))
        this.new_tag.push(tag_and_new_tag.split('=')[1].replace(' ', ''))
        this.tagQuantity.push('')
      })
    }
    if (this.changesDTO.removed_tag) {
      this.changesDTO.removed_tag.replace('[', '').replace(']', '').split(',').forEach(tag => {
        this.removed_tag.push(tag)
        this.removedTagQuantity.push('')
      })
    }
  }

  addTag() {
    this.tagQuantity.push('')
    this.original_tag.push('new_tag')
    this.new_tag.push('new_tag')
  }

  addRemovedTag() {
    this.removedTagQuantity.push('')
    this.removed_tag.push('new_removed_tag')
  }

  removeTagChange(i: number) {
    this.new_tag.splice(i, 1)
    this.original_tag.splice(i, 1)
    this.tagQuantity.pop()
  }

  restoreTag(i: number) {
    this.removed_tag.splice(i, 1)
    this.removedTagQuantity.pop()
  }

  saveChanges() {
    if (this.tagQuantity.length > 0) {
      let changeString = '[';
      this.original_tag.forEach((el, index) => {
        changeString += `${el}=${this.new_tag[index]}`
        if (index != this.original_tag.length - 1) {
          changeString += ','
        }
      })
      changeString += ']'
      this.changesDTO.tag_name = changeString
    }



    if (this.removed_tag.length > 0) {
      this.changesDTO.removed_tag = `[${this.removed_tag.toString()}]`
    } else {
      this.changesDTO.removed_tag = null
    }



    if (this.changesDTO.id) {
      this.service.update(this.changesDTO).subscribe(changes => {
        this.changesDTO = changes
        this.restoreChanges()
      })
    } else {
      console.log(this.changesDTO)
      this.service.insert(this.changesDTO).subscribe(changes => {
        console.log(changes)
        this.changesDTO = changes
        this.restoreChanges()
      }
      )
    }
  }

}
