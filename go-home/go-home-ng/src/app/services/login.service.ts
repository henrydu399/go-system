import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";
import { lstHeaders } from "../constants/httpConstants";
import { Usuario } from "../models/User";

@Injectable({
    providedIn: 'root'
  })
  export class LoginService {
  
    constructor(private http: HttpClient) {
    }
  
  

    public login(usuario: Usuario): Observable<any> {
        
        return this.http.post<any>(environment.login, usuario, { 'headers': lstHeaders });
      }

  
 

}