import { Injectable } from "@angular/core";
import { NgxUiLoaderService } from "ngx-ui-loader";
import { PrivilegioRolUsuario } from "../models/PrivilegioRolUsuario";

@Injectable({
    providedIn: 'root'
  })
  export class NavbarService {

    private  TITLE_KEY:string = 'parameters-title';
    private  PRIVILEGIO:string = 'parameters-privilegio';
    private privilegioRolUsuario!:PrivilegioRolUsuario;
  
   
  
    constructor(private loader: NgxUiLoaderService) { }
  
    public change(privilegio: PrivilegioRolUsuario){
      this.saveTitle(privilegio.privilegio.nombre);
      this.savePrivilegio(privilegio);
    }



  public saveTitle(title: string) {
    window.sessionStorage.removeItem(this.TITLE_KEY);
    window.sessionStorage.setItem(this.TITLE_KEY, title);
  }

  public getTitle(): string {
    return sessionStorage.getItem(this.TITLE_KEY)!;
  }


  public savePrivilegio(privilegio: PrivilegioRolUsuario) {
    window.sessionStorage.removeItem(this.PRIVILEGIO);
    window.sessionStorage.setItem(this.PRIVILEGIO, JSON.stringify(privilegio) );
  }

  public getPrivilegio(): PrivilegioRolUsuario {
    let privilegio: PrivilegioRolUsuario = JSON.parse(sessionStorage.getItem(this.PRIVILEGIO)!);
    return privilegio;
  }

  public getPrivilegioString(): string  {
    let privilegio: string | null = "";
    privilegio = sessionStorage.getItem(this.PRIVILEGIO)
    if(privilegio === null){
      return "";
    }
    return privilegio;
  }

  }