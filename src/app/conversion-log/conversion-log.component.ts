import { Component, OnInit } from '@angular/core';
import { UserDto } from '../dto/user-dto';
import { ConversionDto } from '../dto/conversion-dto';
import { ConversionService } from '../service/conversion.service';

@Component({
  selector: 'app-conversion-log',
  templateUrl: './conversion-log.component.html',
  styleUrls: ['./conversion-log.component.css']
})
export class ConversionLogComponent implements OnInit {

  conversions: ConversionDto[]

  constructor(private service: ConversionService) { }

  ngOnInit() {
    this.service.getAllByUserId((JSON.parse(localStorage.getItem('user')) as UserDto).id)
    .subscribe(conversions => this.conversions = conversions)
  }

}
