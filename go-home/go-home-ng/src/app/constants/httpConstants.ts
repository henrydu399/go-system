import { HttpHeaders } from "@angular/common/http";

export const environment = {
    sistemaName:'ADMINISTRADOR USUARIOS',
    production: true,
    loginIsRequeride :true,
    urlgateway :'http://192.168.1.150:8080'
    
  };

  export  const  lstHeaders= new HttpHeaders()
  .set('content-type', 'application/json; charset=utf-8')
  .set('Access-Control-Allow-Origin', '*');
  