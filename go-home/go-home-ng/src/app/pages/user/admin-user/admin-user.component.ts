import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Usuario } from 'src/app/models/User';
import { MessagesService } from 'src/app/services/menssages.service';
import { UserService } from 'src/app/services/user.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {


  public columnsNames:string[]=["",""];

  public listUser:Usuario[]=[];

  // ###TABLE CONTROL## //
  public wordSearhUsuario:string = "";
  public pages: number = 1;
  public total: number = 0;
   // ####################### //
  constructor(

    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private userService:UserService,
    private utilHttpService:UtilHttpService,
    private router: Router,

  ) { }

  ngOnInit(): void {

    this.getAllUsuarios();
  }


  public buscarUsuario(value : string){
    this.wordSearhUsuario = value;
  }

  public crear(){
    this.router.navigate(['/app/createUser']);
  }

  public editar(usuario:Usuario){
    let url:string = '/app/editarUser/'+usuario.id?.id+'/'+usuario.id?.idTipoIdentificacion+'/'+usuario.id?.numeroIdentificacion;
    this.router.navigate([url]);
  }



  //METODO QUE CONSULTA LOS USUARIOS DEL SISTEMA
  TASK_GET_ALL_USUARIOS = "TASK_GET_ALL_USUARIOS";
  private getAllUsuarios(){
    this.loader.start(this.TASK_GET_ALL_USUARIOS);
    this.userService.getAllUsuarios().subscribe({
      next: (result) => {        
        this.listUser = result;  
        this.loader.stop(this.TASK_GET_ALL_USUARIOS);
      },
      error: (error) => {
        this.utilHttpService.errorManager(error,this.TASK_GET_ALL_USUARIOS);
        this.loader.stop(this.TASK_GET_ALL_USUARIOS);
      }
    });
  }

}
