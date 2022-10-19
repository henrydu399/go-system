import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";
import { lstHeaders } from "../constants/httpConstants";
import { Usuario } from "../models/User";

@Injectable({
    providedIn: 'root'
  })
  export class RegisterService {
  
    constructor(private http: HttpClient) {
    }
  
  

    public register(usuario: Usuario): Observable<any> {
        let body = JSON.stringify(usuario).toString();
  
        return this.http.post<any>(environment.register, body, { 'headers': lstHeaders });
      }

  
 

}