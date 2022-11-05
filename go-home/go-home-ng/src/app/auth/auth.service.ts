import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Usuario } from '../models/User';
import { MenuService } from '../services/menu.service';
import { AutentificacionService } from '../services/autentificacion.service';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  

  private  TOKEN_KEY:string = 'auth-token';
  private  USER_KEY:string = 'auth-user';
  private  TOKEN_EXPIRE:string = 'auto-token-expire';



  constructor(private http: HttpClient,
    private router: Router,
    private loader: NgxUiLoaderService,
    private menuService:MenuService ,
    private autentificacionService :AutentificacionService
  ) { }
 

  login(usuario: Usuario) {
    
    this.menuService.createMenu(usuario);
    
    this.saveToken(usuario.token!);
    usuario.token = null;
    usuario.privilegios = [];
    this.saveUser(usuario);


    let tokenString  =  JSON.parse(atob(this.getToken()!.split('.')[1]));

    this.saveTokenExpire(tokenString.exp);

 
    
    //localStorage.setItem(this.TOKEN_KEY, usuario.token!);
    //localStorage.setItem(this.USER_KEY,  JSON.stringify(usuario));
   

  }

  private CONS_TASK_METODO_LOGOUT: string = "CONS_TASK_METODO_LOGOUT";
  logout(): void {
    this.loader.start(this.CONS_TASK_METODO_LOGOUT);
    sessionStorage.removeItem(this.TOKEN_KEY);
    sessionStorage.removeItem(this.USER_KEY);
   
    sessionStorage.clear();
    window.sessionStorage.clear();

    this.autentificacionService.signOut();
    this.loader.stop(this.CONS_TASK_METODO_LOGOUT)

    this.router.navigate(['/']);
  }


  public saveToken(token: string) {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(this.TOKEN_KEY)!;
  }

 

  public saveUser(user:Usuario) {
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  public getUser():Usuario | null {
    let s:string = window.sessionStorage.getItem(this.USER_KEY)!;
    let u:Usuario = JSON.parse(s);
    return u;
  }

  public saveTokenExpire(_token_expire:number){
    window.sessionStorage.removeItem(this.TOKEN_EXPIRE);
    window.sessionStorage.setItem(this.TOKEN_EXPIRE, JSON.stringify(String(_token_expire) ));
  }


  public getTokenExpire():number{
    return JSON.parse(sessionStorage.getItem(this.TOKEN_EXPIRE)!);
  }


}
