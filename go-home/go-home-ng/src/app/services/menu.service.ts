import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Usuario } from '../models/User';
import { PrivilegioRolUsuario } from '../models/PrivilegioRolUsuario';
import { find } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class MenuService {
  lstHeaders = { headers: new HttpHeaders() }
  private _tokenInicioSesion: any | string;

  private   privilegios:PrivilegioRolUsuario[] = [];

  private  MENU_KEY:string = 'auth-menu';

  constructor(private http: HttpClient,
    private router: Router,
    private loader: NgxUiLoaderService,
  ) {

    let t = this.privilegios.length;
   }

  public createMenu(usuario: Usuario):void{

    this.privilegios = [];
    let privilegiosTemp:PrivilegioRolUsuario[] = [];

    privilegiosTemp = usuario.privilegios;

    //1 LEVEL 
    for( let p of privilegiosTemp){
      if( p.privilegio.level===1){
        this.privilegios.push(p);
        
      }
    }
    //2 LEVEL 
    for( let p of privilegiosTemp){
      
      if( p.privilegio.level===2){
        
        let posicion :number | null = this.findPrivilegioByID(this.privilegios , p.privilegio.fkIdPrivilegio);
        if( posicion !== null){
          if( this.privilegios[posicion].listPrivilegios === undefined || this.privilegios[posicion].listPrivilegios === null){
            this.privilegios[posicion].listPrivilegios=[]; 
          }
          this.privilegios[posicion].listPrivilegios.push(p);
       
        }   
      }
    }
    
    console.log(this.privilegios)
    this.savePrivilegios(this.privilegios);
  }

  private findPrivilegioByID(privilegiosTemp:PrivilegioRolUsuario[] , id :number):number | null{
    let posicion:number = 0;
    for( let p of privilegiosTemp){
      if( p.privilegio.id === id){
        return posicion;
      }
      posicion ++;
    }
    return null;

  }
 

  public savePrivilegios(privilegios: PrivilegioRolUsuario[]) {
    let t:string = JSON.stringify(privilegios);
    window.sessionStorage.removeItem(this.MENU_KEY);
    window.sessionStorage.setItem(this.MENU_KEY, t);
  }

  public getPrivilegios(): PrivilegioRolUsuario[] {
    
    let s : string = sessionStorage.getItem(this.MENU_KEY)!;
    let list : PrivilegioRolUsuario[] = JSON.parse(s);
    return list;
  }




}