import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { TipoIdentificacion } from 'src/app/models/TipoIdentificacion';
import { MessagesService } from 'src/app/services/menssages.service';
import { TipoIdentificacionService } from 'src/app/services/tipo-identificacion.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {


  public tipoIdentificacionSeleccionado!:TipoIdentificacion;

  public fromUsuario = this.formBuilder.group({

    tipoIdentificacion: ['',[Validators.required]],
    numeroIdentificacion: ['',[Validators.required]],
    nombres: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    apellidos: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    email: ['', [Validators.required , Validators.pattern(environment.patternEmail)]],
    movil: ['',[Validators.required]],
    password :['', [Validators.required]],
    passwordtwo :['', [Validators.required]],

  });


  constructor(
    private formBuilder: FormBuilder,
    private utilHttpService:UtilHttpService,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private tipoIdentificacionService:TipoIdentificacionService

  ) { }

  ngOnInit(): void {
 
  }


   //EVENTOS RECIBIDOS DESDE EL CHILD
   update(_tipoIdentificacion :TipoIdentificacion){
    this.tipoIdentificacionSeleccionado = _tipoIdentificacion;    
    this.fromUsuario.controls['tipoIdentificacion'].setValue(_tipoIdentificacion.nombre );

  }



  public crear(){

  }

  public  cerrar(){

  }


   


}
