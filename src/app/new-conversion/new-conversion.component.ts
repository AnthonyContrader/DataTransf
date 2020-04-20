import { Component, OnInit } from '@angular/core';
import { ConversionDto } from '../dto/conversion-dto';
import { ConversionService } from '../service/conversion.service';
import { ChangesDto } from '../dto/changes-dto';
import { ChangesService } from '../service/changes.service';
import { UserDto } from '../dto/user-dto';

@Component({
  selector: 'app-new-conversion',
  templateUrl: './new-conversion.component.html',
  styleUrls: ['./new-conversion.component.css']
})
export class NewConversionComponent implements OnInit {

  new_conversion = new ConversionDto()

  new_changes = new ChangesDto()

  original_tag = new Array<string>()

  original_tag_quantity = new Array()

  new_tag = new Array<string>()

  removed_tag_quantity = new Array()

  removed_tag = new Array<string>()

  removed_tag_index = new Array<number>()

  isOutput = false

  output: string

  constructor(private service: ConversionService, private changesService: ChangesService) { }

  ngOnInit() {
    if(new URLSearchParams(window.location.search).get('id')){
      this.restoreConversion()
    }else {
      this.new_changes.user = (JSON.parse(localStorage.getItem('user')) as UserDto).id
      this.new_conversion.idUser = (JSON.parse(localStorage.getItem('user')) as UserDto).id
    }
    
  }

  restoreConversion(){
    this.service.read(parseInt(new URLSearchParams(window.location.search).get('id'))).subscribe(conversion=>{
        this.new_conversion = conversion
        this.changesService.read(conversion.changes).subscribe(changes=>{
          this.new_changes = changes
          if(changes.changes){
            changes.changes.replace('[', '').replace(']', '').split(',').forEach(tag_and_new_tag=>{
              this.original_tag.push(tag_and_new_tag.split('=')[0].replace(' ', ''))
              this.new_tag.push(tag_and_new_tag.split('=')[1].replace(' ', ''))
              this.original_tag_quantity.push('')
            })
          }
          if(changes.removed){
            changes.removed.replace('[','').replace(']', '').split(',').forEach(tag=>{
              this.removed_tag.push(tag)
              this.removed_tag_index.push(this.original_tag.indexOf(tag))
              this.original_tag.splice(this.original_tag.indexOf(tag), 1)
              this.new_tag.splice(this.original_tag.indexOf(tag),1)
              this.original_tag_quantity.pop()
              this.removed_tag_quantity.push('')
            })
          }
          this.getOutputString()
        })
      })
  }

  getSourceOutput() {
    console.log(this.new_conversion.source)
  }

  getOutput() {
    this.getOriginalTag()
    this.getOutputString()
  }

  getOutputString() {
    if (this.new_conversion.sourceType && this.new_conversion.source) {
      switch (this.new_conversion.sourceType.toString()) {
        case 'XML':
          this.xml2json();
          break
        case 'JSON':
          this.json2xml()
          break;
      }

      this.isOutput = true
    }
  }

  xml2json() {
    let domElement = new DOMParser().parseFromString(`${this.new_conversion.source}`, 'text/xml')



    this.removed_tag.forEach(el => {
      domElement.querySelectorAll(el).forEach(remove => {
        remove.remove()
      })
    })


    this.original_tag.forEach((el, i) => {
      domElement.firstElementChild.innerHTML = domElement.firstElementChild.innerHTML
        .replace(new RegExp(`<${el}>`, 'g'), `<${this.new_tag[i]}>`).replace(new RegExp(`</${el}>`, 'g'), `</${this.new_tag[i]}>`)

    })

    this.output = this.XmlToJson(domElement.firstElementChild).replace(new RegExp(`}{`, 'g'), `},{`).replace(new RegExp('{{', 'g'), '{').replace(new RegExp('}}', 'g'), '}').replace(new RegExp('""', 'g'), '","').replace(/\\/g, "\\\\").replace(/\n/g, "\\n").replace(/\r/g, "\\r").replace(/\t/g, "\\t").replace(/\f/g, "\\f").replace(/'/g, "\\\'").replace(/\&/g, "\\&");
  }

  json2xml() {

    let outputSource = this.jsonToXml(this.new_conversion.source)

    let domElement = new DOMParser().parseFromString(`<root>${outputSource}</root>`, 'text/xml')

    this.removed_tag.forEach(el => {

      domElement.querySelectorAll(el).forEach(remove => {
        remove.remove()
      })
    })


    this.original_tag.forEach((el, i) => {
      domElement.querySelectorAll(el).forEach(tag => {
        tag.outerHTML = `<${this.new_tag[i]}>${tag.innerHTML}</${this.new_tag[i]}>`
      })
    })

    this.output = domElement.firstElementChild.innerHTML

  }

  jsonToXml(json): string {
    let jsonObj = JSON.parse(json)
    let xmlDoc = document.createElement('body')

    function createXmlDom(node, obj) {

      if (Array.isArray(obj)) {
        let parentNode = node.parentElement
        obj.forEach(el => {

          let tmpNode = node.cloneNode()
          createXmlDom(tmpNode, el)
          parentNode.appendChild(tmpNode)
        })
        node.remove()
      } else if (typeof obj == 'object') {
        for (let key in obj) {
          createXmlDom(node.appendChild(document.createElement(key)), obj[key])
        }
      } else {
        node.textContent = obj
      }
      return node
    }

    return createXmlDom(xmlDoc, jsonObj).innerHTML
  }

  removeTag(i: number) {
    this.removed_tag.push(this.original_tag[i])
    this.removed_tag_index.push(i)
    this.removed_tag_quantity.push(i)
    this.original_tag.splice(i, 1)
    this.new_tag.splice(i, 1)
    this.original_tag_quantity.pop()
    this.getOutputString()
  }

  replaceTag(i: number) {
    this.original_tag.splice(this.removed_tag_index[i], 0, this.removed_tag[i])
    this.new_tag.splice(this.removed_tag_index[i], 0, this.removed_tag[i])
    this.removed_tag_index.splice(i, 1)
    this.removed_tag.splice(i, 1)
    this.original_tag_quantity.push('')
    this.removed_tag_quantity.pop()
    this.getOutputString()
  }


  getTagList() {
    if (this.new_conversion.source) {


      var dom = new DOMParser().parseFromString(this.new_conversion.source, 'text/xml')


      let tagList = new Map()

      Array.from(dom.all).forEach(el => {
        if (!tagList.get(el.tagName)) {
          tagList.set(el.tagName, el.tagName)
        }
      })


    }
  }

  getOriginalTag() {

    if (this.new_conversion.source && this.new_conversion.sourceType) {
      let tagList: Array<string> = new Array()
      let newTagList: Array<string> = new Array()
      let tagQuantity = new Array()
      switch (this.new_conversion.sourceType.toString()) {
        case 'XML':
          var dom = new DOMParser().parseFromString(this.new_conversion.source, 'text/xml')


          if (!dom.querySelector('parsererror')) {
            Array.from(dom.all).forEach(el => {
              if (!tagList.includes(el.tagName)) {
                tagList.push(el.tagName)
                newTagList.push(el.tagName)
                tagQuantity.push('')
              }
            })
          }


          this.original_tag = tagList
          this.new_tag = newTagList
          this.original_tag_quantity = tagQuantity
          break;
        case 'JSON':
          let json = JSON.parse(this.new_conversion.source)

          this.getJsonKey(json, tagList, newTagList, tagQuantity)

          this.original_tag = tagList
          this.new_tag = newTagList
          this.original_tag_quantity = tagQuantity
          break;
      }
    }
  }

  getJsonKey(obj: object, keyList: Array<string>, tagList: Array<string>, tagQuantity: Array<any>) {
    for (const key in obj) {
      if (typeof obj[key] == 'object') {
        this.getJsonKey(obj[key], keyList, tagList, tagQuantity)
      }
      if (!keyList.includes(key) && isNaN(parseFloat(key))) {
        keyList.push(key)
        tagList.push(key)
        tagQuantity.push('')
      }
    }
  }

  save() {
    this.generateChanges()
    this.generateConversion()
    if (this.new_changes.id) {
      this.changesService.update(this.new_changes).subscribe(changes => this.new_changes = changes)
      
      if(this.new_conversion.changes != this.new_changes.id){
        this.new_conversion.changes = this.new_changes.id
      }
      
      this.new_conversion.idConversion ? this.service.update(this.new_conversion).subscribe(conversion => this.new_conversion = conversion) : this.service.insert(this.new_conversion).subscribe(conversion=>this.new_conversion=conversion)

    }else {
      
      this.changesService.insert(this.new_changes).subscribe(() => {
        this.changesService.getLastId(this.new_changes.user).subscribe(lastIdChanges=>{
         this.new_conversion.changes = lastIdChanges
         this.new_changes.id = lastIdChanges
         this.service.insert(this.new_conversion).subscribe(() =>{
           this.service.getLastIdByUser(this.new_conversion.idUser).subscribe(id_conversion => this.new_conversion.idConversion = id_conversion)
         })
        })
      })

    }
  }

  generateChanges() {
    if (this.original_tag) {
      this.new_changes.changes = '['
      this.original_tag.forEach((tag, i) => {
        i < this.original_tag.length - 1 ? this.new_changes.changes += `${tag}=${this.new_tag[i]},` : this.new_changes.changes += `${tag}=${this.new_tag[i]}`
      })
      this.new_changes.changes += ']'
    }

    if (this.removed_tag){
      this.new_changes.removed = `[${this.removed_tag.toString()}]`
    }

  }

  generateConversion(){
    switch(this.new_conversion.sourceType.toString()){
      case 'XML':
        this.new_conversion.sourceType = 0
        break
      case 'JSON':
        this.new_conversion.sourceType = 1
        break;
    }
    
    switch(this.new_conversion.outputType.toString()){
      case 'XML':
        this.new_conversion.outputType = 0
        break
      case 'JSON':
        this.new_conversion.outputType = 1
        break;
    }
  }

  XmlToJson(node: Element) {

    let json = ''

    if (node.children.length > 1) {
      json += `{"${node.nodeName}":[{`
      Array.from(node.children).forEach(child => {
        json += `${this.XmlToJson(child as Element)}`
      })
      json += "}]}"
    } else if (node.children.length == 1) {
      Array.from(node.children).forEach(child => {
        json += `{"${node.nodeName}":${this.XmlToJson(child as Element)}}`
      })
    } else {
      json += `"${node.nodeName}":"${node.textContent}"`
    }

    return json
  }

}
