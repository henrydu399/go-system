import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Usuario } from 'src/app/models/User';
import { UsuarioPK } from 'src/app/models/UsuarioPK';
import { MessagesService } from 'src/app/services/menssages.service';
import { UserService } from 'src/app/services/user.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-confirmar-user',
  templateUrl: './confirmar-user.component.html',
  styleUrls: ['./confirmar-user.component.css']
})
export class ConfirmarUserComponent implements OnInit {


  public estado:string = "No confirmado";
  public icono:string = "fa fa-clock-o fa-lg";


  private usuario!:Usuario ;
  
  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private userService:UserService,
    private utilHttpService:UtilHttpService
  ) { }


  TASK_INIT = "TASK_INIT";
  ngOnInit(): void {


    this.route.paramMap.subscribe((params) => {
      if (params.has('idUsuario')) {
        try {
          let idUsuario: number = parseInt(params.get('idUsuario')!) ;
          let idTipoIdentificacion: number = parseInt(params.get('idTipoIdentificacion')!) ;
          let numeroIdentificacion: string = params.get('numeroIdentificacion')!;
          let token: string = params.get('token')! ;

          let pk: UsuarioPK = new UsuarioPK(idUsuario, idTipoIdentificacion, numeroIdentificacion);

          this.usuario =  new Usuario();
          this.usuario.id = pk;
          this.usuario.tokenActivate = token;

          this.userService.confirmUSer(this.usuario).subscribe({
            next: (result) => {
              
              this.estado= "Confirmado";
              this.icono = "fa fa-check";
              this.loader.stop(this.TASK_INIT);

        
            },
            error: (error) => {
              this.utilHttpService.errorManager(error,this.TASK_INIT);
            },
            complete: () => {
              this.loader.stop(this.TASK_INIT);
            }
          });
      


    
          
        } catch (error) {
          this.loader.stop(this.TASK_INIT);
          console.log(error);
        }
      }else{
        this.loader.stop(this.TASK_INIT);
        this.msgService.lanzarAlerta(mensaje.ERROR, 'error confirmando Usuario verifique Url' , 0);
      }
      
    });

  }

}
