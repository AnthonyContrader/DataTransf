import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ConversionDTO } from 'src/dto/conversiondto';
import { UserDTO } from 'src/dto/userdto';
import { ChangesDTO } from 'src/dto/changesdto';
import { Sourcetype } from 'src/dto/sourcetype';
import { Observable, fromEvent } from 'rxjs';
import { ConversionService } from 'src/service/conversion.service';

@Component({
  selector: 'app-newconversion',
  templateUrl: './newconversion.component.html',
  styleUrls: ['./newconversion.component.css']
})
export class NewconversionComponent implements OnInit {

  conversion: ConversionDTO = new ConversionDTO()
  getOutput: boolean = false
  outPut: string

  @ViewChild('SourceRef') sourceRef: ElementRef;
  search: string;   // text to search (email)


  constructor(private service: ConversionService) { }

  submit(text) {
    console.log('submit', text);
    this.search = text;
  }

  ngOnInit() {
    
    localStorage.removeItem('new_changes');
    window.addEventListener('new_changes_saved', () => this.getOutputString())
    this.conversion.idUser = (JSON.parse(localStorage.getItem('currentUser')) as UserDTO).id
  }

  consoleLog() {
    console.log(this.conversion.source)
  }

  seveToLocalstorage() {
    localStorage.setItem('new_conversion', JSON.stringify(this.conversion))
    window.dispatchEvent(new CustomEvent('new_conversion_source'))
  }

  saveConversion(){
    window.dispatchEvent(new CustomEvent('conversion_require_last_id_changes'))
    this.conversion.changes = parseInt(localStorage.getItem('last_changes_id').valueOf())
    this.service.insert(this.conversion).subscribe(()=> this.conversion)
  }

  getOutputString() {

    if(this.conversion.sourceType){
      switch(this.conversion.sourceType.toString()){
      case 'XML': 
        this.xml2json();
        break
      case 'JSON':
        this.json2xml()
        break;
    }

     this.getOutput = true
    }
  }

  xml2json() {
    const changes = JSON.parse(localStorage.getItem('new_changes')) as ChangesDTO

    let domElement = new DOMParser().parseFromString(this.conversion.source, 'text/xml')

    if (changes && changes.removed){
      changes.removed.replace('[', '').replace(']', '').split(',').forEach(el=>{

        domElement.querySelectorAll(el).forEach(remove=>{
          remove.remove()
        })

      })
    }

    if (changes && changes.changes) {
      changes.changes.replace('[', '').replace(']', '').split(',').forEach(el=>{
        let tmp = el.split('=')

        if(tmp){
         domElement.firstElementChild.outerHTML = domElement.firstElementChild.outerHTML.replace(new RegExp(`<${tmp[0]}>`, 'g'), `<${tmp[1]}>`).replace(new RegExp(`</${tmp[0]}>`, 'g'), `</${tmp[1]}>`)
        }

      })
    }

    this.outPut = JSON.stringify(this.xmlToJson(domElement))

  }

  json2xml(){

    const changes = JSON.parse(localStorage.getItem('new_changes')) as ChangesDTO

    let outputSource = this.conversion.source

    if (changes && changes.changes) {
      changes.changes.replace('[', '').replace(']', '').split(',').forEach(el=>{
        let tmp = el.split('=')

        if(tmp){
         outputSource = outputSource.replace(new RegExp(`"${tmp[0]}":`, 'g'), `"${tmp[1]}":`)
        }

      })
    }
    
    this.outPut = this.jsonToXml(outputSource)

  }

 jsonToXml(json): string {
    let jsonObj = JSON.parse(json)
    let xmlDoc = document.createElement('body')
    
    function createXmlDom(node, obj){
    
    if(Array.isArray(obj)){
      
      obj.forEach(el=>{
        let parentNode = node.parentElement
        let tmpNode = node.cloneNode()
        for(let key in el){
          if(typeof el[key] == 'string'){
            tmpNode.appendChild(document.createElement(key)).textContent = el[key]
          }else{
            let mhh = createXmlDom(tmpNode.appendChild(document.createElement(key)),obj[key])
          }
        }
        parentNode.appendChild(tmpNode)
      })
      node.remove()
    }else {
       for(let key in obj){
        createXmlDom(node.appendChild(document.createElement(key)),obj[key])
      }
    }
   
    return node

  }

    return createXmlDom(xmlDoc,jsonObj).innerHTML
  }

  xmlToJson(xml) {
    // Create the return object
    var obj = {};

    if (xml.nodeType == 1) {
      // element
      // do attributes
      if (xml.attributes.length > 0) {
        obj["@attributes"] = {};
        for (var j = 0; j < xml.attributes.length; j++) {
          var attribute = xml.attributes.item(j);
          obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
        }
      }
    } else if (xml.nodeType == 3) {
      // text
      obj = xml.nodeValue;
    }

    // do children
    // If all text nodes inside, get concatenated text from them.
    var textNodes = [].slice.call(xml.childNodes).filter(function (node) {
      return node.nodeType === 3;
    });
    if (xml.hasChildNodes() && xml.childNodes.length === textNodes.length) {
      obj = [].slice.call(xml.childNodes).reduce(function (text, node) {
        return text + node.nodeValue;
      }, "");
    } else if (xml.hasChildNodes()) {
      for (var i = 0; i < xml.childNodes.length; i++) {
        var item = xml.childNodes.item(i);
        var nodeName = item.nodeName;
        if (typeof obj[nodeName] == "undefined") {
          obj[nodeName] = this.xmlToJson(item);
        } else {
          if (typeof obj[nodeName].push == "undefined") {
            var old = obj[nodeName];
            obj[nodeName] = [];
            obj[nodeName].push(old);
          }
          obj[nodeName].push(this.xmlToJson(item));
        }
      }
    }
    return obj;
  }

}
