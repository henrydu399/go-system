import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { Departamento } from '../models/Departamento';
import { Persona } from '../models/Persona';
import { RolesUsuario } from '../models/RolesUsuario';
import { Usuario } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class RolUsuarioService {

  constructor(private http: HttpClient) {
  }



  public getAll(): Observable<any> {
    return this.http.get<any>(environment.pathRolUsuario,{ 'headers': lstHeaders } );
  }

  public getAllBySystema(systemaName:string): Observable<any> {
    return this.http.get<any>(environment.findRolUsuarioBySystemaName+ systemaName ,{ 'headers': lstHeaders } );
  }
  
  public save(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.post<any>(environment.pathRolUsuario, body, { 'headers': lstHeaders });
  }

  public edith(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.put<any>(environment.pathRolUsuario , body, { 'headers': lstHeaders });
  }
 


  public findCustom(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.post<any>(environment.pathRolUsuario, body, { 'headers': lstHeaders });
  }

  public findById(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.post<any>(environment.findRolUsuarioById, body, { 'headers': lstHeaders });
  }

  public deleteUser(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.post<any>(environment.pathRolUsuario, body, { 'headers': lstHeaders });
  }

  public desactivateUser(rolUsuario: RolesUsuario): Observable<any> {
    let body = JSON.stringify(rolUsuario).toString();
    return this.http.post<any>(environment.pathRolUsuario, body, { 'headers': lstHeaders });
  }




}
