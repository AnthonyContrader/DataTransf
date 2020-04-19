import { Component, OnInit } from '@angular/core';
import { ConversionDto } from 'src/app/dto/conversion-dto';
import { ConversionService } from 'src/app/service/conversion.service';

@Component({
  selector: 'app-all-conversion-log',
  templateUrl: './all-conversion-log.component.html',
  styleUrls: ['./all-conversion-log.component.css']
})
export class AllConversionLogComponent implements OnInit {

  conversions: ConversionDto[]

  constructor(private service: ConversionService) { }

  ngOnInit() {
    this.service.getAll().subscribe(conversions => this.conversions = conversions)
  }

}
