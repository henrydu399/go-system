import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { Departamento } from '../models/Departamento';
import { Persona } from '../models/Persona';
import { Usuario } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  constructor(private http: HttpClient) {
  }



  public getAll(): Observable<any> {
    return this.http.get<any>(environment.pathPersonas,{ 'headers': lstHeaders } );
  }
  
  public save(persona: Persona): Observable<any> {
    let body = JSON.stringify(persona).toString();
    return this.http.post<any>(environment.pathPersonas, body, { 'headers': lstHeaders });
  }

  public edith(persona: Persona): Observable<any> {
    let body = JSON.stringify(persona).toString();
    return this.http.put<any>(environment.pathPersonas , body, { 'headers': lstHeaders });
  }
 


  public findCustom(persona: Persona): Observable<any> {
    let body = JSON.stringify(persona).toString();
    return this.http.post<any>(environment.findPersonas, body, { 'headers': lstHeaders });
  }

  public deleteUser(persona: Persona): Observable<any> {
    let body = JSON.stringify(persona).toString();
    return this.http.post<any>(environment.pathDeletePersona, body, { 'headers': lstHeaders });
  }

  public desactivateUser(usuario: Persona): Observable<any> {
    let body = JSON.stringify(usuario).toString();
    return this.http.post<any>(environment.pathDesactivateUSer, body, { 'headers': lstHeaders });
  }




}
