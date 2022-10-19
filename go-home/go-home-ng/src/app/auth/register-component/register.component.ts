import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Persona } from 'src/app/models/Persona';
import { Usuario } from 'src/app/models/User';
import { MessagesService } from 'src/app/services/menssages.service';
import { RegisterService } from 'src/app/services/register.service';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-register.component',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private usuario! : Usuario ;

  public submitted : boolean = false;

  public formRegister = this.formBuilder.group({
    nombres: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    apellidos: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(60)]],
    email: ['', [Validators.required , Validators.pattern(environment.patternEmail)]],
    password :['', [Validators.required]],
    passwordtwo :['', [Validators.required]],

  });

  constructor(  
    private formBuilder: FormBuilder, private loader: NgxUiLoaderService,private msgService: MessagesService,
    private registerService:RegisterService  , private router: Router
    ) { }

  ngOnInit(): void {
  }

  //METODO QUE DEVUELVE EL VALOR DEL FORMULARIO
  get f(): { [key: string]: AbstractControl } {
    return this.formRegister.controls;
  }

  public isvalidInput(value : string) :  string{
    if(this.submitted){
     if( this.formRegister.get(value)?.errors){
       return 'has-error'
     }
     return 'has-success';
    }
    return '';
  }
  ///////////////////////////////////////
  

  
 
  private CONS_TASK_METODO_REGISTER: string = "CONS_TASK_METODO_REGISTER";
  public register():void{
    this.submitted = true;
    this.loader.start(this.CONS_TASK_METODO_REGISTER);

    if( !this.formRegister.valid){
      this.msgService.lanzarAlerta(mensaje.ERROR, "Error con el formulario", 501);
      this.loader.stop(this.CONS_TASK_METODO_REGISTER);
      return;
    }

    this.build();


    this.registerService.register(this.usuario).subscribe({
      next: (result) => {
        this.loader.stop(this.CONS_TASK_METODO_REGISTER);
        this.msgService.lanzarAlerta(mensaje.SUCCESS, "Registrio exitoso !",200); 
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.loader.stop(this.CONS_TASK_METODO_REGISTER);
        if(error.error && error.error.message){
         this.msgService.lanzarAlerta(mensaje.ERROR, error.error.message,0);
        }else{
          this.msgService.lanzarAlerta(mensaje.ERROR, error.message,500);
        }
      },
      complete: () => {
        this.loader.stop(this.CONS_TASK_METODO_REGISTER);
      }
    });

  }

  private build():void{
    this.usuario = new Usuario();
    this.usuario.persona = new Persona()
    this.usuario.persona.nombres =  this.formRegister.get('nombres')!.value;
    this.usuario.persona.apellidos = this.formRegister.get('apellidos')!.value;
    this.usuario.email = this.formRegister.get('email')!.value;
    this.usuario.password = this.formRegister.get('password')!.value;

  }

}
