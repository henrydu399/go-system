import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { TipoIdentificacion } from 'src/app/models/TipoIdentificacion';
import { MessagesService } from 'src/app/services/menssages.service';
import { TipoIdentificacionService } from 'src/app/services/tipo-identificacion.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-tipo-identificacion',
  templateUrl: './tipo-identificacion.component.html',
  styleUrls: ['./tipo-identificacion.component.css']
})
export class TipoIdentificacionComponent implements OnInit {

  public listTiposDocumentos:TipoIdentificacion[] = [];
  public txtBuscarTipoDocumento:string="";

    //TABLA 
  public search: string = "";
  public page: number = 1;
  public total: number = 0;
  //####


  @Output() newItemEvent = new EventEmitter<TipoIdentificacion>();

  constructor(
    private utilHttpService:UtilHttpService,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private tipoIdentificacionService:TipoIdentificacionService
  ) { }

  ngOnInit(): void {

    this.getAllTiposDocumentos();
  }


  public buscar(value :string){
    this.txtBuscarTipoDocumento = value;
  }

  public seleccionar(tipoIdentificacion:TipoIdentificacion){
    this.enviarPadre(tipoIdentificacion);
  }


  //EVENTO QUE ENVIA EL OBJETO AL PADRE
  private  enviarPadre(value: TipoIdentificacion){
    this.newItemEvent.emit(value);
  }
  //##############################3


      //METODO QUE CONSULTA LOS BARRIOS
      TASK_GET_TIPO_DOCUMENTO = "TASK_GET_TIPO_DOCUMENTO";
      private getAllTiposDocumentos(){
     
        console.log("CARGANDO TIPOS DOCUMENTOS..")
        this.loader.start(this.TASK_GET_TIPO_DOCUMENTO);
        this.tipoIdentificacionService.getAllTiposIdentificacion().subscribe({
          next: (result) => {     
            this.listTiposDocumentos = result; 
            this.loader.stop(this.TASK_GET_TIPO_DOCUMENTO);
            console.log(" TIPOS DOCUMENTOS CARGADOS CORRECTAMENTE")             
          },
          error: (error:any) => {
            this.utilHttpService.errorManager(error,this.TASK_GET_TIPO_DOCUMENTO);
            console.log(" ERROR CARGANDO CORRECTAMENTE")
          }
        });
      }


}
