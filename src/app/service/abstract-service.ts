import { Service } from './service';
import { HttpClient } from '@angular/common/http';

export class AbstractService<DTO> implements Service<DTO> {
    type: string
    port: string = '8080'

    constructor(protected http: HttpClient){
    }

    read(id: number): import("rxjs").Observable<DTO> {
        return this.http.get<DTO>(`http://localhost:${this.port}/${this.type}/read?id=${id}`)
    }
    delete(id: number): import("rxjs").Observable<any> {
        this.delete
        return this.http.delete(`http://localhost:${this.port}/${this.type}/delete?id=${id}`)
    }
    update(dto: DTO): import("rxjs").Observable<any> {
        return this.http.put<DTO>(`http://localhost:${this.port}/${this.type}/update`, dto)
    }
    insert(dto: DTO): import("rxjs").Observable<any> {
        return this.http.post(`http://localhost:${this.port}/${this.type}/insert`,dto)
    }
    getAll(): import("rxjs").Observable<DTO[]> {
        return this.http.get<DTO[]>(`http://localhost:${this.port}/${this.type}/getall`)
    }
}
