import { Component, OnInit } from '@angular/core';
import { ChangesDto } from '../dto/changes-dto';
import { ChangesService } from '../service/changes.service';
import { UserDto } from '../dto/user-dto';

@Component({
  selector: 'app-new-changes',
  templateUrl: './new-changes.component.html',
  styleUrls: ['./new-changes.component.css']
})
export class NewChangesComponent implements OnInit {

  new_changes = new ChangesDto()
  tagQuantity = new Array()
  removedTagQuantity = new Array()
  original_tag = new Array<string>()
  new_tag = new Array<string>()
  removed_tag = new Array<string>()

  constructor(private service: ChangesService) { }

  ngOnInit() {
    this.new_changes.user = (JSON.parse(localStorage.getItem('user')) as UserDto).id
  }

  addTag(){
    this.tagQuantity.push('')
    this.original_tag.push('new_tag')
    this.new_tag.push('new_tag')
  }

  addRemovedTag(){
    this.removedTagQuantity.push('')
    this.removed_tag.push('new_removed_tag')
  }

  removeTagChange(i: number){
    this.new_tag.splice(i, 1)
    this.original_tag.splice(i, 1)
    this.tagQuantity.pop()
  }

  restoreTag(i: number){
    this.removed_tag.splice(i, 1)
    this.removedTagQuantity.pop()
  }

  saveChanges(){
    
    let changeString = '[';
    this.original_tag.forEach((el, index)=>{
      changeString +=`${el}=${this.new_tag[index]}`
      if(index!=this.original_tag.length-1){
        changeString +=','
      }
    })
    changeString += ']'

    this.new_changes.changes = changeString

    if(this.removed_tag.length > 0){
      this.new_changes.removed = `[${this.removed_tag.toString()}]`
    }else {
      this.new_changes.removed = null
    }

    this.service.insert(this.new_changes).subscribe()

  }

}
