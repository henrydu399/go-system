import { Injectable } from "@angular/core";
import { NgxUiLoaderService } from "ngx-ui-loader";
import { Messages } from "../shareds/messages/messages.models";


@Injectable({
    providedIn: 'root'
  })
  export class MessagesService {
 
  
    public listMessages :Messages[] = [];
  
    constructor(private loader: NgxUiLoaderService) { }
  
    /** 
     * 
     * @param tipo Class css 
     * @param mensaje  Texto mensaje error
     * @param code  StatusCode|501 = Internal Error app
     */
    lanzarAlerta(tipo: string, mensaje: string , code:number) {
  
      
      let m: Messages = new Messages();
  
      m.mostrarAlerta = true;
      m.msgTipo = tipo;
      m.msgAlerta = mensaje;

      if(code===503){
        m.msgAlerta = "Servicio indisponible, intente mas tarde";
      }

      if(code===500){
        m.msgAlerta = "Error general con el servidor, intente mas tarde";
      }
  
      this.listMessages.push(m);
  
      this.loader.stop();
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
  
      
      setTimeout(()=>{                           // <<<---using ()=> syntax
      this.eliminarMensaje(m);
      
    }, 5000);
  
    
  
    }
  
  
    public eliminarMensaje(m :Messages){
      this.listMessages.splice(this.findIndex(m.id));
    }
  
    
    private findIndex(value:number):number{
  
    let index = 0;
      if( this.listMessages.length > 0){
  
        for (let element of this.listMessages) {
          if ( element.id == value){
            break;
           } 
           index ++;
        }
  
  
      }
      
      return index;
    }
  
   
  
  
  
  
  }
  