import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { Departamento } from '../models/Departamento';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }



  public getAllUsuarios(): Observable<any> {
    return this.http.get<any>(environment.getAllUsuarios,{ 'headers': lstHeaders } );
  }

 /*  public getActive(): Observable<any> {
    return this.http.get<any>(environment.urlConciliacionResultado + 'active/');
  }
 */

/* 
  public getByIdConciliacion(idConciliacion: number): Observable<any> {
    return this.http.get<any>(environment.urlConciliacionResultadoByIdConciliacion + idConciliacion);
  }

  public getById(idResultado: number): Observable<any> {
    return this.http.get<any>(environment.urlConciliacionResultado + idResultado);
  }


  public save(data: ConConciliacionResultado): Observable<any> {
    let body = JSON.stringify(data).toString();
    return this.http.post<any>(environment.urlConciliacionResultado, body, { 'headers': this.lstHeaders });
  }



  public edith(data: ConConciliacionResultado, id: number): Observable<any> {
    //let body = JSON.stringify(menus).toString();
    return this.http.put<any>(environment.urlConciliacionResultado + id, data, { 'headers': this.lstHeaders });
  }
 */



/* 
  public borrar(idParametrizacion: number) {
    return this.http.delete<any>(environment.urlConciliacionResultado + idParametrizacion, { 'headers': this.lstHeaders });
  }
 */

}
