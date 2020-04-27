import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { UserDTO } from '../dto/user-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from '../dto/login-dto';
import { SignupDTO } from '../dto/signup-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO> {

  constructor(http: HttpClient) {
    super(http)
    this.type = 'users'
    this.port = 8080
  }

  delete(login: string): Observable<any> {
    return this.http.delete(`http://localhost:${this.port}/api/${this.type}/${login}`, {
      headers: {
        Authorization: this.authorization()
      }
    })
  }

  login(loginDTO: LoginDTO): Observable<any> {
    return this.http.post(`http://localhost:${this.port}/api/authenticate`, loginDTO)
  }

  isLogged(loginDTO: LoginDTO, id_token: string): Observable<UserDTO> {
    return this.http.get<UserDTO>(`http://localhost:${this.port}/api/users/${loginDTO.username}`, {
      headers: {
        Authorization: `Bearer ${id_token}`
      }
    })
  }

  signup(signup: SignupDTO) {

    return this.http.post(`http://localhost:${this.port}/api/register`, signup)
   
  }

}
