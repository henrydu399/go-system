import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Barrio } from 'src/app/models/Barrio';
import { Ciudad } from 'src/app/models/Ciudad';
import { Departamento } from 'src/app/models/Departamento';
import { Persona } from 'src/app/models/Persona';
import { PersonaContacto } from 'src/app/models/PersonaContacto';
import { PersonaContactoPK } from 'src/app/models/PersonaContactoPK';
import { PersonaPK } from 'src/app/models/PersonaPK';
import { RolesUsuario } from 'src/app/models/RolesUsuario';
import { RolesUsuarioPK } from 'src/app/models/RolesUsuarioPK';
import { RolSistema } from 'src/app/models/RolSistema';
import { TipoIdentificacion } from 'src/app/models/TipoIdentificacion';
import { Usuario } from 'src/app/models/User';
import { UsuarioPK } from 'src/app/models/UsuarioPK';
import { CacheService } from 'src/app/services/cache.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { RolSistemaService } from 'src/app/services/rol-systema.service';
import { UserService } from 'src/app/services/user.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

   public tipoIdentificacionSeleccionado!:TipoIdentificacion;
   public departamentoSeleccionado!:Departamento | null;
   public ciudadSeleccionada!:Ciudad | null;
   public barrioSeleccionado!:Barrio | null;
   public rolSistemaSeleccionado!: RolSistema;


   public listBarrios:Barrio[] = [];
   public listCiudad:Ciudad[] = [];
   public listDepartamentos:Departamento[] = [];

   public listRolesSistema!:RolSistema[];
   public submitted:boolean = false;
   private usuario!:Usuario ;
  

  public fromUsuario = this.formBuilder.group({
    tipoIdentificacion :['', [Validators.required]],
    numeroIdentificacion :['', [Validators.required]],
    nombres: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    apellidos: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    email: ['', [Validators.required , Validators.pattern(environment.patternEmail)]],
    movil: ['',[Validators.required]],
    fechaNacimiento: [formatDate(new Date(), 'dd/MM/yyyy', 'en'), []],
    rol: ['',],
    edad: ['',],
    sexo: ['',],
    estadoCivil: ['',],
    profesion: ['',],
    nivelEscolaridad: ['',],
    departamento: ['',],
    ciudad: ['',],
    barrio:['',],
    direccion: ['',]

  });


  constructor(
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private userService:UserService,
    private utilHttpService:UtilHttpService,
    private router: Router,
    private formBuilder: FormBuilder,
    private rolSistemaService:RolSistemaService,
    private cacheService :CacheService,
  ) { }

  ngOnInit(): void {
    this.getAllRolesSistema();
    this.listCiudad =  this.cacheService.getCiudades();
    this.listDepartamentos = this.cacheService.getDepartamentos();
    this.listBarrios = this.cacheService.getBarrios();
  }


  //EVENTOS DESDE LOS CHILDS
  public updateTipoIdentificacion(_tipoIdentificacion:TipoIdentificacion){
    this.tipoIdentificacionSeleccionado = _tipoIdentificacion;
    this.fromUsuario.get('tipoIdentificacion')?.setValue(_tipoIdentificacion.nombre);
  }

  public updateDepartamento(_departamento:Departamento){
    ////LIMPIANDO
    this.ciudadSeleccionada = null;
    this.fromUsuario.get('ciudad')?.setValue('');
    this.barrioSeleccionado = null;
    this.fromUsuario.get('barrio')?.setValue('');
    /// ###### //////

    this.departamentoSeleccionado = _departamento;
    this.fromUsuario.get('departamento')?.setValue(_departamento.nombre);

  }

  public updateCiudad(_ciudad:Ciudad){


       ////LIMPIANDO
       this.departamentoSeleccionado = null;
       this.fromUsuario.get('departamento')?.setValue('');
       this.barrioSeleccionado = null;
       this.fromUsuario.get('barrio')?.setValue('');
       /// ###### //////

    this.ciudadSeleccionada = _ciudad;

    let departamento = this.listDepartamentos.find( elemento => elemento.id = _ciudad.id.idDepartamento);
    if( departamento !== undefined  && departamento !== null){
      this.departamentoSeleccionado = departamento!;
      this.fromUsuario.get('departamento')?.setValue(departamento.nombre);
    }

    this.fromUsuario.get('ciudad')?.setValue(_ciudad.nombre);
  }

  public updateBarrio(_barrio:Barrio){
    this.barrioSeleccionado = _barrio;
    this.fromUsuario.get('barrio')?.setValue(_barrio.nombre);

    let departamento = this.listDepartamentos.find( elemento => elemento.id = _barrio.id.idDepartamento);
    if( departamento !== undefined  && departamento !== null){
      this.departamentoSeleccionado = departamento!;
      this.fromUsuario.get('departamento')?.setValue(departamento.nombre);
    }

    let ciudad = this.listCiudad.find( elemento => elemento.id.id = _barrio.id.idCiudad);
    if( ciudad !== undefined  && ciudad !== null){
      this.ciudadSeleccionada = ciudad!;
      this.fromUsuario.get('ciudad')?.setValue(ciudad.nombre);
    }

  }
  //########

  public changeRol(){
    let rolName:string = this.fromUsuario.get('rol')?.value!;
    for( let rolSistema of this.listRolesSistema){
      if( rolSistema.id.nombreRol === rolName){
          this.rolSistemaSeleccionado = new RolSistema();
          this.rolSistemaSeleccionado = rolSistema;
          break;
      } 
    }
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


 
  
  private CONS_TASK_METODO_CREAR: string = "CONS_TASK_METODO_CREAR";
  public crear(){
    
    this.submitted = true;
    this.loader.start(this.CONS_TASK_METODO_CREAR);

    if( !this.fromUsuario.valid){
      this.msgService.lanzarAlerta(mensaje.ERROR, "Error con el formulario", 0);
      this.loader.stop(this.CONS_TASK_METODO_CREAR);
      return;
    }

    if( this.tipoIdentificacionSeleccionado === undefined || this.tipoIdentificacionSeleccionado === null){
      this.msgService.lanzarAlerta(mensaje.ERROR, "Error con el tipo de documento", 0);
      this.loader.stop(this.CONS_TASK_METODO_CREAR);
      return;
    }

    this.build();

    this.userService.saveForSystem(this.usuario).subscribe({
      next: (result) => {
        this.loader.stop(this.CONS_TASK_METODO_CREAR);
        this.msgService.lanzarAlerta(mensaje.SUCCESS, "Proceso exitoso !",200); 
        this.router.navigate(['/app/adminUser']);
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.CONS_TASK_METODO_CREAR);
      },
      complete: () => {
        this.loader.stop(this.CONS_TASK_METODO_CREAR);
      }
    });

  }

  public calcularEdad(){
    let fechaNacimiento:string | null | undefined = this.fromUsuario.get('fechaNacimiento')?.value;
    if( fechaNacimiento !== undefined && fechaNacimiento !== null){
      
      
      var hoy = new Date();
      var cumpleanos = new Date(fechaNacimiento);
      var edad = hoy.getFullYear() - cumpleanos.getFullYear();
      var m = hoy.getMonth() - cumpleanos.getMonth();
  
      if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
          edad--;
      }
      console.log(edad)
      this.fromUsuario.get('edad')?.setValue( String (edad) );
      //return edad;

    }
  }

/*
*    fechaNacimiento: [formatDate(new Date(), 'dd/MM/yyyy', 'en'), [Validators.required]],
    rol: ['',],
    edad: ['',],
    sexo: ['',],
    estadoCivil: ['',],
    profesion: ['',],
    nivelEscolaridad: ['',],
*/
  public build(){
    try {

      let numeroIdentificacion:string = this.fromUsuario.get('numeroIdentificacion')?.value!;
      let personaPk :PersonaPK = new PersonaPK(numeroIdentificacion,this.tipoIdentificacionSeleccionado.id);
  
      let persona:Persona = new Persona();
      persona.id=personaPk;
      persona.nombres = this.fromUsuario.get('nombres')?.value!;
      persona.apellidos = this.fromUsuario.get('apellidos')?.value!;
      
      let fechaNacimiento:string | null | undefined = this.fromUsuario.get('fechaNacimiento')?.value;
      if( fechaNacimiento !== undefined && fechaNacimiento !== null){
        persona.fechaNacimiento = new Date(fechaNacimiento);
      }
      persona.edad = parseInt ( this.fromUsuario.get('edad')?.value! ) ;
      persona.estadoCivil  = this.fromUsuario.get('estadoCivil')?.value!;
      persona.sexo = this.fromUsuario.get('sexo')?.value!;
      persona.profesion = this.fromUsuario.get('profesion')?.value!;
      persona.nivelEscolaridad = this.fromUsuario.get('nivelEscolaridad')?.value!;
  
      console.log( persona.fechaNacimiento);
     
  
      let personaContactoPK:PersonaContactoPK =  new PersonaContactoPK();
      personaContactoPK.idTipoIdentificacion = persona.id.idTipoIdentificacion;
      personaContactoPK.numeroIdentificacion = persona.id.numeroIdentificacion;

      let personaContacto : PersonaContacto = new PersonaContacto();
      personaContacto.id= personaContactoPK;
      personaContacto.idDepartamento = this.departamentoSeleccionado?.id!;
      personaContacto.idCiudad = this.ciudadSeleccionada?.id!.id!;
      personaContacto.idBarrio = this.barrioSeleccionado?.id.id!;
      personaContacto.direccion = this.fromUsuario.get('direccion')?.value!; 
      personaContacto.activo = true;      
  
      let usuarioPk = new UsuarioPK(null,this.tipoIdentificacionSeleccionado.id,numeroIdentificacion );
      this.usuario = new Usuario();
      this.usuario.id= usuarioPk;
      this.usuario.persona = persona;
      this.usuario.email = this.fromUsuario.get('email')?.value!;
      this.usuario.movil = this.fromUsuario.get('movil')?.value!;
      this.usuario.rol = this.rolSistemaSeleccionado;

      let listPersonaContacto: PersonaContacto[] = [];
      listPersonaContacto.push(personaContacto);
      this.usuario.persona.listPersonaContacto =  listPersonaContacto;
      
    } catch (error) {
      console.log(error)
      this.loader.stop(this.CONS_TASK_METODO_CREAR);
    }

  }


  public  cerrar(){
    this.router.navigate(['/app/adminUser']);
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
