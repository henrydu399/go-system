import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AutentificacionService {

  private  TOKEN_KEY:string = 'auth-token';
  private  USER_KEY:string = 'auth-user';

  constructor(private router: Router,) { }


  signOut() {
    window.sessionStorage.clear();
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

  public getUser() {
    return JSON.parse(sessionStorage.getItem(this.USER_KEY)!);
  }

}
