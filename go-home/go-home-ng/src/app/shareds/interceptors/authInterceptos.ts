import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AutentificacionService } from 'src/app/services/autentificacion.service';
import { NavbarService } from 'src/app/services/navbar.service';



const TOKEN_HEADER_KEY = 'Authorization';
const PRIVILEGIO_HEADER_KEY = 'privilegio';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private authService: AutentificacionService,
    private navbarService:NavbarService
  )
  { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    let authReq = req;
    const token = this.authService.getToken();
    if (token != null) {

     

        authReq = req.clone(
          { headers: 
            req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token ),
          }
          );

         authReq = authReq.clone(
          { headers: 
            authReq.headers.set(PRIVILEGIO_HEADER_KEY, this.navbarService.getPrivilegioString()),
          }
          );
 

/* 
        authReq = req.clone();
        authReq.headers.set(PRIVILEGIO_HEADER_KEY, this.navbarService.getPrivilegioString());
        authReq.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token);
         */
        
        

  
 
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
