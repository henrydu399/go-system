import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from '../constants/menssagesConstants';
import { MessagesService } from '../services/menssages.service';

@Injectable({
  providedIn: 'root'
})
export class UtilHttpService {

  constructor(private http: HttpClient , private loader: NgxUiLoaderService,private msgService: MessagesService) {
  }


  public errorManager(error:any, task:string){
    this.loader.stop(task);

    if(error.status === 0 || error.status === 503){
        this.msgService.lanzarAlerta(mensaje.ERROR, "",500);
    }else{
        if(error.error !== undefined && error.error !== null){
            this.msgService.lanzarAlerta(mensaje.ERROR, error.error,0);
          }else{
            this.msgService.lanzarAlerta(mensaje.ERROR, error.message,500);
          }
    }
   

  }

  
 
}