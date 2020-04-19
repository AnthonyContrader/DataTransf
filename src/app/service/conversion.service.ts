import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { ConversionDto } from '../dto/conversion-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConversionService extends AbstractService<ConversionDto> {

  constructor(http: HttpClient) {
    super(http)
    this.type = 'conversion'
   }

   getAllByUserId(id: number): Observable<ConversionDto[]> {
    return this.http.get<ConversionDto[]>(`http://localhost:${this.port}/${this.type}/getAllByIdUser?id=${id}`)
   }

   getLastIdByUser(id: number): Observable<number> {
     return this.http.get<number>(`http://localhost:${this.port}/${this.type}/getLastIdByUser?id=${id}`)
   }
}
