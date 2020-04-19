import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { ChangesDto } from '../dto/changes-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChangesService extends AbstractService<ChangesDto> {

  constructor(http: HttpClient) {
    super(http)
    this.type = 'changes'
   }

   getLastId(id: number): Observable<number> {
    return this.http.get<any>(`http://localhost:${this.port}/${this.type}/getLastId?id=${id}`)
  }

  getAllByUser(id: number): Observable<ChangesDto[]> {
    return this.http.get<any>(`http://localhost:${this.port}/${this.type}/getAllByUser?user=${id}`)
  }

}
