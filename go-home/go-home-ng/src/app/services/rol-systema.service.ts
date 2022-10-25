import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';

@Injectable({
  providedIn: 'root'
})
export class RolSistemaService {

  constructor(private http: HttpClient) {
  }



  public getAllRolSistema(): Observable<any> {
    return this.http.get<any>(environment.getAllRolesSistema,{ 'headers': lstHeaders } );
  }
}
