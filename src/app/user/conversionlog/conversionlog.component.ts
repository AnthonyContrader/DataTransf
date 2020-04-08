import { Component, OnInit } from '@angular/core';
import { ConversionDTO } from 'src/dto/conversiondto';
import { ConversionService } from 'src/service/conversion.service'
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-conversionlog',
  templateUrl: './conversionlog.component.html',
  styleUrls: ['./conversionlog.component.css']
})
export class ConversionlogComponent implements OnInit {

  conversions: ConversionDTO[]

  constructor(private service: ConversionService) { }

  ngOnInit() {
    this.getAll()
  }

  getAll(): void {
    this.service.findAllByIdUser((JSON.parse(localStorage.getItem('currentUser')) as UserDTO).id).subscribe(conversions => this.conversions = conversions)
  }

}
