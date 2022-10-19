import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { AuthService } from 'src/app/auth/auth.service';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Usuario } from 'src/app/models/User';
import { LoginService } from 'src/app/services/login.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';
import { environment } from 'src/environments/environment.prod';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {


  private usuario! : Usuario ;

  public submitted : boolean = false;

  public formRegister = this.formBuilder.group({
    email: ['', [Validators.required , Validators.pattern(environment.patternEmail)]],
    password :['', [Validators.required]],

  });

  constructor(
    private formBuilder: FormBuilder, private loader: NgxUiLoaderService,private msgService: MessagesService,
    private loginService:LoginService , private authService : AuthService , private router: Router,
    private utilHttpService:UtilHttpService
    ){}

    ngOnInit(): void {
      if(this.authService.getUser() !== null){
        this.router.navigate(['/']);
      }
    }
  

    //***************UTILS FORMULARIO******** */
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

    private CONS_TASK_METODO_LOGIN: string = "CONS_TASK_METODO_LOGIN";
  public login(){

    this.submitted = true;
    this.loader.start(this.CONS_TASK_METODO_LOGIN);

    if( !this.formRegister.valid){
      this.msgService.lanzarAlerta(mensaje.ERROR, "Error con el formulario", 501);
      this.loader.stop(this.CONS_TASK_METODO_LOGIN);
      return;
    }


    this.build();

    this.loginService.login(this.usuario).subscribe({
      next: (result) => {
        
        this.loader.stop(this.CONS_TASK_METODO_LOGIN);
        let  user:Usuario = new Usuario();
        user = result;
        this.authService.login(user);
        this.msgService.lanzarAlerta(mensaje.SUCCESS, "login exitoso !",200); 
        this.router.navigate(['/']);
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.CONS_TASK_METODO_LOGIN);
      },
      complete: () => {
        this.loader.stop(this.CONS_TASK_METODO_LOGIN);
      }
    });


  }
  private build():void{
    this.usuario = new Usuario();
    this.usuario.email = this.formRegister.get('email')!.value;
    this.usuario.password = this.formRegister.get('password')!.value;

  }




}
