import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { ConversionDTO } from 'src/dto/conversiondto';
import { Observable } from 'rxjs'

@Injectable({
    providedIn: 'root'
  })
export class ConversionService extends AbstractService<ConversionDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'conversion';
    }

    findAllByIdUser(id: number): Observable<ConversionDTO[]> {
        return this.http.post<any>('http://localhost:8080/' + this.type + '/getAllByIdUser', id)
    }

}