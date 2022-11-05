import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Persona } from 'src/app/models/Persona';
import { Usuario } from 'src/app/models/User';
import { MessagesService } from 'src/app/services/menssages.service';
import { PersonaService } from 'src/app/services/persona.service';
import { UserService } from 'src/app/services/user.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-admin-persona',
  templateUrl: './admin-persona.component.html',
  styleUrls: ['./admin-persona.component.css']
})
export class AdminPersonaComponent implements OnInit {

  public columnsNames:string[]=["",""];

  public listPersona:Persona[]=[];

  // ###TABLE CONTROL## //
  public wordSearhPersona:string = "";
  public pages: number = 1;
  public total: number = 0;
   // ####################### //
  constructor(

    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private personaService:PersonaService,
    private utilHttpService:UtilHttpService,
    private router: Router,

  ) { }

  ngOnInit(): void {
    this.getAllUsuarios();
  }


  public buscarUsuario(value : string){
    this.wordSearhPersona = value;
  }

  public crear(){
    this.router.navigate(['/app/createPersona']);
  }

  public editar(persona:Persona){
    let url:string = '/app/editarPersona/'+persona.id?.idTipoIdentificacion+'/'+persona.id?.numeroIdentificacion;
    this.router.navigate([url]);
  }

  private CONS_TASK_USER_DELETE: string = "CONS_TASK_USER_DELETE";
  public delete(persona:Persona){
    this.personaService.deleteUser(persona).subscribe({
      next: (result) => {
        this.loader.stop(this.CONS_TASK_USER_DELETE);
        this.msgService.lanzarAlerta(mensaje.SUCCESS, "Proceso exitoso !",200); 
        this.getAllUsuarios();
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.CONS_TASK_USER_DELETE);
      },
      complete: () => {
        this.loader.stop(this.CONS_TASK_USER_DELETE);
      }
    });
  }

  private CONS_TASK_USER_DESACTIVATE: string = "CONS_TASK_USER_DESACTIVATE";
  public desactivate(persona:Persona){
    this.personaService.desactivateUser(persona).subscribe({
      next: (result) => {
        this.loader.stop(this.CONS_TASK_USER_DESACTIVATE);
        this.msgService.lanzarAlerta(mensaje.SUCCESS, "Proceso exitoso !",200); 
        this.getAllUsuarios();
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.CONS_TASK_USER_DESACTIVATE);
      },
      complete: () => {
        this.loader.stop(this.CONS_TASK_USER_DESACTIVATE);
      }
    });
  }



  //METODO QUE CONSULTA LOS USUARIOS DEL SISTEMA
  TASK_GET_ALL_USUARIOS = "TASK_GET_ALL_USUARIOS";
  private getAllUsuarios(){
    this.loader.start(this.TASK_GET_ALL_USUARIOS);
    this.personaService.getAll().subscribe({
      next: (result) => {        
        this.listPersona = result;  
        this.loader.stop(this.TASK_GET_ALL_USUARIOS);
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.TASK_GET_ALL_USUARIOS);
        this.loader.stop(this.TASK_GET_ALL_USUARIOS);
      }
    });
  }

}
