import { Injectable, inject } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { ChangesDTO } from 'src/dto/changesdto';

@Injectable({
    providedIn: 'root'
})

export class ChangesService extends AbstractService<ChangesDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'changes';
    }

}