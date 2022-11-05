import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AutentificacionService {

  private  TOKEN_KEY:string = 'auth-token';
  private  USER_KEY:string = 'auth-user';
  private  TOKEN_EXPIRE:string = 'auto-token-expire';

  constructor(private router: Router,) { }


  signOut() {
    window.sessionStorage.clear();
  }

/*   public saveToken(token: string) {
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

  public getUser() {
    return JSON.parse(sessionStorage.getItem(this.USER_KEY)!);
  }

  public saveTokenExpire(_token_expire:number){
    window.sessionStorage.removeItem(this.TOKEN_EXPIRE);
    window.sessionStorage.setItem(this.TOKEN_EXPIRE, JSON.stringify(String(_token_expire) ));
  }


  public getTokenExpire():number{
    return JSON.parse(sessionStorage.getItem(this.TOKEN_EXPIRE)!);
  } */

}
