import { Component, OnInit, Input } from '@angular/core';
import { ChangesDTO } from 'src/dto/changesdto';
import { ConversionDTO } from 'src/dto/conversiondto';
import { ChangesService } from 'src/service/changes.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-newchanges',
  templateUrl: './newchanges.component.html',
  styleUrls: ['./newchanges.component.css']
})
export class NewchangesComponent implements OnInit {

  
  @Input() conversion: ConversionDTO

  

  changes = new ChangesDTO()

  originalTag: Array<string>
  newTag: Array<string> = new Array()

  removedTag: Array<string> = new Array()

  constructor(private service: ChangesService) { }

  ngOnInit() {
    window.addEventListener('new_conversion_source', ()=> this.start())

    window.addEventListener('conversion_require_last_id_changes', ()=> this.inserChanges())
    
    this.changes.user = this.conversion.idUser 
  }

  start(){
    this.getOriginalTag()
  }

  inserChanges(){
    this.saveChanges()
    this.service.insert(this.changes).subscribe(()=>this.getLastId())
  }

  getLastId(){
    this.service.getLastId((JSON.parse(localStorage.getItem('currentUser')) as UserDTO).id).subscribe(id=>localStorage.setItem('last_changes_id', id.toString()))
  }

  getTagList() {
    if(this.conversion.source){

    
      var dom = new DOMParser().parseFromString(this.conversion.source, 'text/xml')

      
        let tagList = new Map()

        Array.from(dom.all).forEach(el=>{
          if(!tagList.get(el.tagName)){
            tagList.set(el.tagName, el.tagName)
          }
        })
      

    }
  }

  getOriginalTag(){

    if(this.conversion.source && this.conversion.sourceType){
        let tagList: Array<string> = new Array()
        let newTagList: Array<string> = new Array()
        switch(this.conversion.sourceType.toString()){
          case 'XML':
              var dom = new DOMParser().parseFromString(this.conversion.source, 'text/xml')

              
              if(!dom.querySelector('parsererror')){
                Array.from(dom.all).forEach(el=>{
                  if(!tagList.includes(el.tagName)){
                    tagList.push(el.tagName)
                    newTagList.push(el.tagName)
                  }
                })
              }
              

              this.originalTag = tagList
              this.newTag = newTagList
            break;
          case 'JSON':
              let json = JSON.parse(this.conversion.source)

              this.getJsonKey(json, tagList, newTagList)

              this.originalTag = tagList
              this.newTag = newTagList
            break;
        }
    }
  }

  getJsonKey(obj: object, keyList: Array<string>, tagList: Array<string>){
    for (const key in obj) {
      if(typeof obj[key] == 'object'){
        this.getJsonKey(obj[key], keyList, tagList)
      }
      if(!keyList.includes(key) && isNaN(parseFloat(key))){
        keyList.push(key)
        tagList.push(key)
      }
    }
  }

  saveChanges(){
    let changeString = '[';
    this.originalTag.forEach((el, index)=>{
      changeString +=`${el}=${this.newTag[index]}`
      if(index!=this.originalTag.length-1){
        changeString +=','
      }
    })
    changeString += ']'

    this.changes.changes = changeString

    if(this.removedTag.length > 0){
      this.changes.removed = `[${this.removedTag.toString()}]`
    }else {
      this.changes.removed = null
    }
    

    localStorage.setItem('new_changes', JSON.stringify(this.changes))

    window.dispatchEvent(new CustomEvent('new_changes_saved'))

  }

  consoleLog(){
    console.log(this.newTag)
    console.log(this.conversion.source)
  }

  removeTag(tag: string){
    if(!this.removedTag.includes(tag)){
      this.removedTag.push(tag)
    }
    this.saveChanges()
  }

  replaceTag(tag: string){
    if(this.removedTag.includes(tag)){
      this.removedTag.splice(this.removedTag.indexOf(tag), 1)
    }
    this.saveChanges
  }
  
}
