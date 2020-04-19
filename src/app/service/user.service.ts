import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDto } from '../dto/login-dto';
import { UserDto } from '../dto/user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDto>{

  constructor(http: HttpClient) { 
    super(http);
    this.type = 'user'
  }

  login(loginDTO: LoginDto): Observable<UserDto>{
    return this.http.post<UserDto>(`http://localhost:${this.port}/${this.type}/login`, loginDTO)
  }

}
