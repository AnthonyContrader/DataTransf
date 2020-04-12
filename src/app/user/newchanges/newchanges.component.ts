import { Component, OnInit, Input } from '@angular/core';
import { ChangesDTO } from 'src/dto/changesdto';
import { ConversionDTO } from 'src/dto/conversiondto';

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

  constructor() { }

  ngOnInit() {
    //this.conversion = JSON.parse(localStorage.getItem('new_conversion'))

    window.addEventListener('new_conversion_source', ()=> this.start())
    
    this.changes.user = this.conversion.idUser
    //this.getOriginalTag()
 
  }

  start(){
    this.getOriginalTag()
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
    if(this.conversion.source){
      
      var dom = new DOMParser().parseFromString(this.conversion.source, 'text/xml')

      let tagList: Array<string> = new Array()

      Array.from(dom.all).forEach(el=>{
        if(!tagList.includes(el.tagName)){
          tagList.push(el.tagName)
          this.newTag.push(el.tagName)
        }
      })

      this.originalTag = tagList

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

    this.changes.removed = `[${this.removedTag.toString()}]`

    localStorage.setItem('new_changes', JSON.stringify(this.changes))

  }

  consoleLog(){
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
