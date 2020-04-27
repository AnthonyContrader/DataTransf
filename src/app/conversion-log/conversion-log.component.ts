import { Component, OnInit } from '@angular/core';
import { ConversionDTO } from '../dto/conversion-dto';
import { ConversionService } from '../service/conversion.service';
import { UserDTO } from '../dto/user-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-conversion-log',
  templateUrl: './conversion-log.component.html',
  styleUrls: ['./conversion-log.component.css']
})
export class ConversionLogComponent implements OnInit {

  conversionDTO: ConversionDTO[]

  constructor(private service: ConversionService, private router: Router) { }

  ngOnInit() {
    this.service.readAllByUser((JSON.parse(localStorage.getItem('user')) as UserDTO).id).subscribe(conversions => this.conversionDTO = conversions)
  }

  goToConversion(id: number) {
    this.router.navigate([`${(JSON.parse(localStorage.getItem('user')) as UserDTO).authorities.includes('ROLE_ADMIN') ? 'admin' : 'user'}-dashboard/newconversion`],
      { queryParams: { id: id } })
  }

}
