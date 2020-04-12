import { Injectable, inject } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { ChangesDTO } from 'src/dto/changesdto';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class ChangesService extends AbstractService<ChangesDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'changes';
    }

    getLastId(id: number): Observable<number> {
      return this.http.get<any>(`http://localhost:8080/${this.type}/getLastId?id=${id}`)
    }

    getAllByUser(id: number): Observable<ChangesDTO[]> {
      return this.http.get<any>(`http://localhost:8080/${this.type}/getAllByUser?user=${id}`)
    }

}