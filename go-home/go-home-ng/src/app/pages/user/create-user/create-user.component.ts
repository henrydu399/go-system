import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Persona } from 'src/app/models/Persona';
import { PersonaPK } from 'src/app/models/PersonaPK';
import { TipoIdentificacion } from 'src/app/models/TipoIdentificacion';
import { Usuario } from 'src/app/models/User';
import { MessagesService } from 'src/app/services/menssages.service';
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
   public submitted:boolean = false;
   private usuario!:Usuario ;
  

  public fromUsuario = this.formBuilder.group({
    tipoIdentificacion :['', [Validators.required]],
    numeroIdentificacion :['', [Validators.required]],
    nombres: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    apellidos: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    email: ['', [Validators.required , Validators.pattern(environment.patternEmail)]],
    movil: ['',[Validators.required]],
    fechaNacimiento: ['',],
    rol: ['',],
    edad: ['',],
    sexo: ['',],
    estadoCivil: ['',],
    profesion: ['',],
    nivelEscolaridad: ['',],
    departamento: ['',],
    ciudad: ['',],
    direccion: ['',],
    password :['', [Validators.required]],
    passwordtwo :['', [Validators.required]],

  });


  constructor(
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private userService:UserService,
    private utilHttpService:UtilHttpService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
  }


  //EVENTOS DESDE LOS CHILDS
  public updateTipoIdentificacion(_tipoIdentificacion:TipoIdentificacion){
    this.tipoIdentificacionSeleccionado = _tipoIdentificacion;
    this.fromUsuario.get('tipoIdentificacion')?.setValue(_tipoIdentificacion.nombre);
  }
  //########


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


  }


  public build(){
    let numeroIdentificacion:string = this.fromUsuario.get('numeroIdentificacion')?.value!;
    let personaPk :PersonaPK = new PersonaPK(numeroIdentificacion,this.tipoIdentificacionSeleccionado.id);


    let persona:Persona = new Persona();
    persona.id=personaPk;
    persona.nombres = this.fromUsuario.get('nombres')?.value!;
    persona.apellidos = this.fromUsuario.get('apellidos')?.value!;
    persona.fecha_nacimiento = Date.parse(this.fromUsuario.get('fechaNacimiento')?.value!);
    persona.

    this.usuario = new Usuario();

  }


  public  cerrar(){

  }

}
