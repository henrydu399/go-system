import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Barrio } from 'src/app/models/Barrio';
import { BarrioPK } from 'src/app/models/BarrioPk';
import { Ciudad } from 'src/app/models/Ciudad';
import { Departamento } from 'src/app/models/Departamento';
import { Persona } from 'src/app/models/Persona';
import { PersonaContacto } from 'src/app/models/PersonaContacto';
import { PersonaContactoPK } from 'src/app/models/PersonaContactoPK';
import { PersonaPK } from 'src/app/models/PersonaPK';
import { RolSistemaPK } from 'src/app/models/RolesSistemaPK';
import { RolesUsuario } from 'src/app/models/RolesUsuario';
import { RolesUsuarioPK } from 'src/app/models/RolesUsuarioPK';
import { RolSistema } from 'src/app/models/RolSistema';
import { TipoIdentificacion } from 'src/app/models/TipoIdentificacion';
import { Usuario } from 'src/app/models/User';
import { UsuarioPK } from 'src/app/models/UsuarioPK';
import { CacheService } from 'src/app/services/cache.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { RolSistemaService } from 'src/app/services/rol-systema.service';
import { RolUsuarioService } from 'src/app/services/rol-usuario.service';
import { UserService } from 'src/app/services/user.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-edith-rol-usuario',
  templateUrl: './edith-rol-usuario.component.html',
  styleUrls: ['./edith-rol-usuario.component.css']
})
export class EdithRolUsuarioComponent implements OnInit {

  public roleUsuario!: RolesUsuario;

  public estado: boolean = false;

  public submitted:boolean = false;

  public listRolesSistema!:RolSistema[];



  public fromUsuario = this.formBuilder.group({
    tipoIdentificacion: ['', [Validators.required]],
    numeroIdentificacion: ['', [Validators.required]],
    nombres: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(60)]],
    apellidos: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(60)]],
    email: ['', [Validators.required, Validators.pattern(environment.patternEmail)]],
    movil: ['', [Validators.required]],
    rol: ['',],

  });
  constructor(
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private userService: UserService,
    private utilHttpService: UtilHttpService,
    private router: Router,
    private formBuilder: FormBuilder,
    private rolSistemaService: RolSistemaService,
    private rolUsuarioService: RolUsuarioService,
    private cacheService: CacheService,
    private route: ActivatedRoute
  ) { }

  TASK_EDITH_ROL_USER_INIT = "TASK_EDITH_ROL_USER_INIT";
  ngOnInit(): void {
    this.getAllRolesSistema();

    this.loader.start(this.TASK_EDITH_ROL_USER_INIT);
    this.route.paramMap.subscribe((params) => {
      if (params.has('id') && params.has('idRolSistema') && params.has('idSistema') &&
        params.has('nombreRol') && params.has('idUsuario') && params.has('idTipoIdentificacion') &&
        params.has('numeroIdentificacion')
      ) {
        try {
          let id: number = parseInt(params.get('id')!);
          let idRolSistema: number = parseInt(params.get('idRolSistema')!);
          let idSistema: number = parseInt(params.get('idSistema')!);
          let nombreRol: string = params.get('nombreRol')!;
          let idUsuario: number = parseInt(params.get('idUsuario')!);
          let idTipoIdentificacion: number = parseInt(params.get('idTipoIdentificacion')!);
          let numeroIdentificacion: string = params.get('numeroIdentificacion')!;



          let pk: RolesUsuarioPK = new RolesUsuarioPK();
          pk.id = id;
          pk.idRolSistema = idRolSistema;
          pk.idSistema = idSistema;
          pk.nombreRol = nombreRol;
          pk.idUsuario = idUsuario;
          pk.idTipoIdentificacion = idTipoIdentificacion;
          pk.numeroIdentificacion = numeroIdentificacion;

          let rolUsuario: RolesUsuario = new RolesUsuario();
          rolUsuario.id = pk;

          this.rolUsuarioService.findById(rolUsuario).subscribe({
            next: (result) => {
              this.loader.stop(this.TASK_EDITH_ROL_USER_INIT);

              let rolUsuarioFind: RolesUsuario = result;
              if (rolUsuarioFind === undefined || rolUsuarioFind === null) {
                this.msgService.lanzarAlerta(mensaje.ERROR, 'No se encontro un rol usuario con la informacion entrante', 0);
              }

              this.roleUsuario = rolUsuarioFind;

              this.fromUsuario.get('tipoIdentificacion')?.setValue(rolUsuarioFind.usuario.persona?.tipoIdentificacion.nombre!);
              this.fromUsuario.get('numeroIdentificacion')?.setValue(rolUsuarioFind.usuario.persona?.id.numeroIdentificacion!);
              this.fromUsuario.get('nombres')?.setValue(rolUsuarioFind.usuario.persona?.nombres!);
              this.fromUsuario.get('apellidos')?.setValue(rolUsuarioFind.usuario.persona?.apellidos!);
              this.fromUsuario.get('email')?.setValue(rolUsuarioFind.usuario.email!);
              this.fromUsuario.get('movil')?.setValue(rolUsuarioFind.usuario.movil!);

              if (rolUsuarioFind.id !== undefined ) {
                this.fromUsuario.get('rol')?.setValue(rolUsuarioFind.id.nombreRol!);
              }


              console.log(result);

            },
            error: (error) => {
              this.utilHttpService.errorManager(error, this.TASK_EDITH_ROL_USER_INIT);
            },
            complete: () => {
              this.loader.stop(this.TASK_EDITH_ROL_USER_INIT);
            }
          });


        } catch (error) {
          this.loader.stop(this.TASK_EDITH_ROL_USER_INIT);
          console.log(error);
        }
      } else {
        this.loader.stop(this.TASK_EDITH_ROL_USER_INIT);
        this.msgService.lanzarAlerta(mensaje.ERROR, 'error no se ha seleccionado un  rol Usuario verifique Url', 0);
      }

    });

  }

 //METODO QUE DEVUELVE EL VALOR DEL FORMULARIO
 get f(): { [key: string]: AbstractControl } {
  return this.fromUsuario.controls;
}
public isvalidInput(value : string) :  string{
  if(this.submitted){
   if( this.fromUsuario.get(value)?.errors){
     return 'has-error'
   }
   return 'has-success';
  }
  return '';
}
///////////////////////////////////////

 

public editar(){

}


public  cerrar(){
  this.router.navigate(['/app/adminRolUsuario']);
}



public changeRol(){
  let rolName:string = this.fromUsuario.get('rol')?.value!;
  for( let rolSistema of this.listRolesSistema){
/*     if( rolSistema.id.nombreRol === rolName){
        this.rolSistemaSeleccionado = new RolSistema();
        this.rolSistemaSeleccionado = rolSistema;
        break;
    }  */
  }
}

       //METODO QUE CONSULTA LOS ROLES
       TASK_GET_ROL_SISTEMA = "TASK_GET_ROL_SISTEMA";
       private getAllRolesSistema(){
         console.log("CARGANDO ROLES DE SISTEMA..")
         this.loader.start(this.TASK_GET_ROL_SISTEMA);
         this.rolSistemaService.getAllRolSistema().subscribe({
           next: (result) => {     
             this.listRolesSistema = result; 
             this.loader.stop(this.TASK_GET_ROL_SISTEMA);
             console.log(" ROLES DE SISTEMA CARGADOS CORRECTAMENTE")             
           },
           error: (error:any) => {
             this.utilHttpService.errorManager(error,this.TASK_GET_ROL_SISTEMA);
             console.log(" ERROR CARGANDO ")
           }
         });
       }



}
