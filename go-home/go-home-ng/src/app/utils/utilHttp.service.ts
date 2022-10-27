import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { AuthService } from '../auth/auth.service';
import { mensaje } from '../constants/menssagesConstants';
import { AutentificacionService } from '../services/autentificacion.service';
import { MessagesService } from '../services/menssages.service';

@Injectable({
  providedIn: 'root'
})
export class UtilHttpService {

  constructor(private http: HttpClient , private loader: NgxUiLoaderService,private msgService: MessagesService , private authService:AuthService) {
  }


  public errorManager(error:any, task:string){
    this.loader.stop(task);

    if(error.status === 0 || error.status === 503){
        this.msgService.lanzarAlerta(mensaje.ERROR, "",500);
    }else{

      if(error.status===403 ){
        this.msgService.lanzarAlerta(mensaje.ERROR, "Usuario no autorizado, cerrando session ........",0);

        setTimeout(() => {
          this.authService.logout();
        }, 2000);
        

      }else{
        
        if(error.error !== undefined && error.error !== null){

          if( error.error.message !== undefined || error.error.message !== null){
            this.msgService.lanzarAlerta(mensaje.ERROR, error.error.message,0);
          }else{
            this.msgService.lanzarAlerta(mensaje.ERROR, error.error,0);
          }
         
        }else{
          this.msgService.lanzarAlerta(mensaje.ERROR, error.message,500);
        }
      }

    }
   

  }

  
 
}