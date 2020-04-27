import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChangesService } from '../service/changes.service';
import { ConversionDTO } from '../dto/conversion-dto';
import { ChangesDTO } from '../dto/changes-dto';
import { ConversionService } from '../service/conversion.service';
import { UserDTO } from '../dto/user-dto';

@Component({
  selector: 'app-new-conversion',
  templateUrl: './new-conversion.component.html',
  styleUrls: ['./new-conversion.component.css']
})
export class NewConversionComponent implements OnInit {

  conversionDTO = new ConversionDTO()

  changesPreset: ChangesDTO[]

  presetIndex: string

  new_changes = new ChangesDTO()

  original_tag = new Array<string>()

  original_tag_quantity = new Array()

  new_tag = new Array<string>()

  removed_tag_quantity = new Array()

  removed_tag = new Array<string>()

  removed_tag_index = new Array<number>()

  isOutput = false

  output: string

  constructor(private service: ConversionService, private changesService: ChangesService, private router: ActivatedRoute) { }

  ngOnInit() {

    this.router.queryParams.subscribe(param => {
      if (param.id) {
        this.service.read(param.id).subscribe(conversion => this.conversionDTO = conversion)
      } else {
        this.conversionDTO.usr = (JSON.parse(localStorage.getItem('user')) as UserDTO).id
      }
    })

    this.changesService.readAllByUser((JSON.parse(localStorage.getItem('user')) as UserDTO).id).subscribe(changes => this.changesPreset = changes)

  }

  getSourceOutput() {
    console.log(this.conversionDTO.source)
  }

  getOutput() {
    this.getOriginalTag()
    this.getOutputString()
  }

  getOutputString() {
    if (this.conversionDTO.sourcetype && this.conversionDTO.source) {
      switch (this.conversionDTO.sourcetype) {
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
    let domElement = new DOMParser().parseFromString(`${this.conversionDTO.source}`, 'text/xml')



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

    let outputSource = this.jsonToXml(this.conversionDTO.source)

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
    if (this.conversionDTO.source) {


      var dom = new DOMParser().parseFromString(this.conversionDTO.source, 'text/xml')


      let tagList = new Map()

      Array.from(dom.all).forEach(el => {
        if (!tagList.get(el.tagName)) {
          tagList.set(el.tagName, el.tagName)
        }
      })


    }
  }

  getOriginalTag() {

    if (this.conversionDTO.source && this.conversionDTO.sourcetype) {
      let tagList: Array<string> = new Array()
      let newTagList: Array<string> = new Array()
      let tagQuantity = new Array()
      switch (this.conversionDTO.sourcetype.toString()) {
        case 'XML':
          var dom = new DOMParser().parseFromString(this.conversionDTO.source, 'text/xml')


          if (!dom.querySelector('parsererror')) {
            Array.from(dom.all).forEach(el => {
              if (!tagList.includes(el.tagName)) {
                tagList.push(el.tagName)
                newTagList.push(el.tagName)
                tagQuantity.push('')
              }
            })
          }



          break;
        case 'JSON':
          let json = JSON.parse(this.conversionDTO.source)

          this.getJsonKey(json, tagList, newTagList, tagQuantity)


          break;
      }
      this.original_tag = tagList
      this.new_tag = newTagList
      this.original_tag_quantity = tagQuantity
      this.removed_tag = new Array<string>()
      this.removed_tag_index = new Array<number>()
      this.removed_tag_quantity = new Array()
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

    if (this.original_tag.length > 0) {
      this.conversionDTO.tagname = '['
      this.original_tag.forEach((tag, i) => {
        this.conversionDTO.tagname += `${tag}=${this.new_tag[i]}${i < this.original_tag.length - 1 ? ',' : ']'}`
      })
    }

    if (this.removed_tag.length > 0) {
      this.conversionDTO.removedtag = `[${this.removed_tag.toString()}]`
    }

    if (this.conversionDTO.id) {
      this.service.update(this.conversionDTO).subscribe(conversion => this.conversionDTO = conversion)
    } else {
      this.service.insert(this.conversionDTO).subscribe(conversion => this.conversionDTO = conversion)
    }
  }

  generateChanges() {
    if (this.original_tag) {
      this.new_changes.tag_name = '['
      this.original_tag.forEach((tag, i) => {
        i < this.original_tag.length - 1 ? this.new_changes.tag_name += `${tag}=${this.new_tag[i]},` : this.new_changes.tag_name += `${tag}=${this.new_tag[i]}`
      })
      this.new_changes.tag_name += ']'
    }

    if (this.removed_tag) {
      this.new_changes.removed_tag = `[${this.removed_tag.toString()}]`
    }

  }

  generateConversion() {

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

  getPreset() {
    if (this.presetIndex) {
      this.getOriginalTag()

      this.changesPreset[parseInt(this.presetIndex)].tag_name.replace('[', '').replace(']', '').split(',').forEach(el => {
        if (this.original_tag.includes(el.split('=')[0])) {
          this.new_tag[this.original_tag.indexOf(el.split('=')[0])] = el.split('=')[1]
        } else {
          this.original_tag.push(el.split('=')[0])
          this.new_tag.push(el.split('=')[1])
          this.original_tag_quantity.push('')
        }
      })

      this.changesPreset[parseInt(this.presetIndex)].removed_tag.replace('[', '').replace(']', '').split(',').forEach(el => {
        if (!this.removed_tag.includes(el)) {
          this.removed_tag.push(el)
          this.removed_tag_quantity.push('')
        }
      })

      this.getOutputString()
    } else {
      this.getOriginalTag()
    }
  }

}
