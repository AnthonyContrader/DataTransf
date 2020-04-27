import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { ConversionDTO } from '../dto/conversion-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConversionService extends AbstractService<ConversionDTO>{

  constructor(http: HttpClient) {
    super(http)
    this.port = 8080
    this.type = 'convs'
    this.name = 'conv'
  }

  readAllByUser(id: number): Observable<ConversionDTO[]> {
    return this.http.get<ConversionDTO[]>(`http://localhost:${this.port}/${this.name}/api/usr/${id}`, {
      headers: {
        Authorization: this.authorization()
      }
    })
  }
}
