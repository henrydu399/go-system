import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';

@Injectable({
  providedIn: 'root'
})
export class TipoIdentificacionService {

  constructor(private http: HttpClient) {
  }



  public getAllTiposIdentificacion(): Observable<any> {
    return this.http.get<any>(environment.getAllTipoDocumentos,{ 'headers': lstHeaders } );
  }
}
