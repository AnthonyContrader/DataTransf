import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { ChangesDTO } from '../dto/changes-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChangesService extends AbstractService<ChangesDTO> {

  constructor(http: HttpClient) {
    super(http)
    this.port = 8080
    this.type = 'changes'
    this.name = 'changes'
  }

  readAllByUser(id: number): Observable<ChangesDTO[]> {
    return this.http.get<ChangesDTO[]>(`http://localhost:${this.port}/changes/api/user/${id}`, {
      headers: {
        Authorization: this.authorization()
      }
    })
  }

}
