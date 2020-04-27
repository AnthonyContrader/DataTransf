import { Service } from './service';

import {HttpClient} from '@angular/common/http'
import { UserDTO } from '../dto/user-dto';
import { Observable } from 'rxjs';

export class AbstractService<DTO> implements Service<DTO> {
    
    type: string
    port: number
    name: string

    constructor(protected http: HttpClient){
    }

    authorization(){
        if (localStorage.getItem('id_token')) {
            return 'Bearer ' + localStorage.getItem('id_token');
        } else {
        return '';
        }   
    }

    read(id: number): Observable<DTO> {
        return this.http.get<DTO>(`http://localhost:${this.port}${this.name? `/${this.name}` :  ''}/api/${this.type}/${id}`, {
            headers: {
                Authorization: this.authorization()
            }
        })
    }

    delete(id: number | string): Observable<any> {
        return this.http.delete(`http://localhost:${this.port}${this.name? `/${this.name}` :  ''}/api/${this.type}/${id}`, {
            headers: {
                Authorization: this.authorization()
            }
        })
    }

    update(dto: DTO): Observable<any> {
        return this.http.put(`http://localhost:${this.port}${this.name? `/${this.name}` :  ''}/api/${this.type}`, dto, {
            headers: {
                Authorization: this.authorization()
              }
        })
    }
    
    insert(dto: DTO): Observable<any> {
        return this.http.post<DTO>(`http://localhost:${this.port}${this.name? `/${this.name}` :  ''}/api/${this.type}`, dto, {
            headers: {
                Authorization: this.authorization()
            }
        })
    }
    
    getAll(): Observable<DTO[]> {
        return this.http.get<DTO[]>(`http://localhost:${this.port}${this.name? `/${this.name}` :  ''}/api/${this.type}`, {
            headers: {
                Authorization: this.authorization()
            }
        })
    }

}
