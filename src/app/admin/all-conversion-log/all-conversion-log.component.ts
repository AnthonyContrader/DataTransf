import { Component, OnInit } from '@angular/core';
import { ConversionDTO } from 'src/app/dto/conversion-dto';
import { ConversionService } from 'src/app/service/conversion.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/dto/user-dto';

@Component({
  selector: 'app-all-conversion-log',
  templateUrl: './all-conversion-log.component.html',
  styleUrls: ['./all-conversion-log.component.css']
})
export class AllConversionLogComponent implements OnInit {

  conversionDTO: ConversionDTO[]

  constructor(private service: ConversionService, private router: Router) { }

  ngOnInit() {
    this.service.getAll().subscribe(conversions => this.conversionDTO = conversions)
  }

  goToConversion(id: number) {
    this.router.navigate([`${(JSON.parse(localStorage.getItem('user')) as UserDTO).authorities.includes('ROLE_ADMIN') ? 'admin' : 'user'}-dashboard/newconversion`],
      { queryParams: { id: id } })
  }

}
