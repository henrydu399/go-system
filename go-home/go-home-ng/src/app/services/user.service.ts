import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { Departamento } from '../models/Departamento';
import { Usuario } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }



  public getAllUsuarios(): Observable<any> {
    return this.http.get<any>(environment.pathUsuarios,{ 'headers': lstHeaders } );
  }
  

  public confirmUSer(usuario: Usuario): Observable<any> {
    //let body = JSON.stringify(data).toString();
    return this.http.post<any>(environment.confirmUser, usuario, { 'headers': lstHeaders });
  }




  public save(usuario: Usuario): Observable<any> {
    let body = JSON.stringify(usuario).toString();
    return this.http.post<any>(environment.pathUsuarios, body, { 'headers': lstHeaders });
  }

  public saveForSystem(usuario: Usuario): Observable<any> {
    let body = JSON.stringify(usuario).toString();
    return this.http.post<any>(environment.pathSaveForSystem, body, { 'headers': lstHeaders });
  }

  public edithForSystem(usuario: Usuario): Observable<any> {
    let body = JSON.stringify(usuario).toString();
    return this.http.post<any>(environment.pathEdithForSystem, body, { 'headers': lstHeaders });
  }



  public findCustom(usuario: Usuario): Observable<any> {
    let body = JSON.stringify(usuario).toString();
    return this.http.post<any>(environment.findUser, body, { 'headers': lstHeaders });
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
