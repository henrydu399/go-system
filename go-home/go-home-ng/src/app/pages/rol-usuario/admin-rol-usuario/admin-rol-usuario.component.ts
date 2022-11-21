import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { RolesUsuario } from 'src/app/models/RolesUsuario';
import { MessagesService } from 'src/app/services/menssages.service';
import { PersonaService } from 'src/app/services/persona.service';
import { RolUsuarioService } from 'src/app/services/rol-usuario.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';
import { Global } from 'src/environments/environment.prod';

@Component({
  selector: 'app-admin-rol-usuario',
  templateUrl: './admin-rol-usuario.component.html',
  styleUrls: ['./admin-rol-usuario.component.css']
})
export class AdminRolUsuarioComponent implements OnInit {


  public listRolesUsuario!: RolesUsuario[];

    // ###TABLE CONTROL## //
    public wordSearhPersona:string = "";
    public pages: number = 1;
    public total: number = 0;
     // ####################### //

  constructor(
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private personaService:PersonaService,
    private rolUsuarioService:RolUsuarioService,
    private utilHttpService:UtilHttpService,
    private router: Router,

  ) { }

  ngOnInit(): void {
    this.getAllUsuarios();
  }

  /**
   * 
   * @param rolesUsuario 
   * params.has('id') && params.has('idRolSistema') && params.has('idSistema') &&
        params.has('nombreRol') && params.has('idUsuario') && params.has('idTipoIdentificacion') &&
        params.has('numeroIdentificacion')
   */

  public editar(rolesUsuario:RolesUsuario){
      let url:string = '/app/editarRolUsuario/'+rolesUsuario.id.id +'/'+rolesUsuario.id.idRolSistema +'/'+ rolesUsuario.id.idSistema +'/'+ 
      rolesUsuario.id.nombreRol +'/'+ rolesUsuario.id.idUsuario +'/'+ rolesUsuario.id.idTipoIdentificacion +'/'+ rolesUsuario.id.numeroIdentificacion
      this.router.navigate([url]);
    
  }


  public delete(rolesUsuario:RolesUsuario){

  }

  


    //METODO QUE CONSULTA LOS ROLES X USUARIO DEL SISTEMA
    TASK_GET_ALL_ROLES_USUARIOS = "TASK_GET_ALL_ROLES_USUARIOS";
    private getAllUsuarios(){
      this.loader.start(this.TASK_GET_ALL_ROLES_USUARIOS);
      this.rolUsuarioService.getAllBySystema(Global.sistemaName).subscribe({
        next: (result) => {        
          this.listRolesUsuario = result;  
          this.loader.stop(this.TASK_GET_ALL_ROLES_USUARIOS);
        },
        error: (error) => {
          this.utilHttpService.errorManager(error,this.TASK_GET_ALL_ROLES_USUARIOS);
          this.loader.stop(this.TASK_GET_ALL_ROLES_USUARIOS);
        }
      });
    }



}
