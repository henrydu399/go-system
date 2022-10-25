import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Departamento } from 'src/app/models/Departamento';
import { CacheService } from 'src/app/services/cache.service';
import { HomeService } from 'src/app/services/home.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { TipoIdentificacionService } from 'src/app/services/tipo-identificacion.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-departamento',
  templateUrl: './departamento.component.html',
  styleUrls: ['./departamento.component.css']
})
export class DepartamentoComponent implements OnInit {


  @Output() newItemEvent = new EventEmitter<Departamento>();

public listDepartamentos:Departamento[] = [];

public txtBuscarDepartamento:string="";

//TABLA 
public search: string = "";
public page: number = 1;
public total: number = 0;
//####

  constructor(

    private utilHttpService:UtilHttpService,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private cacheService:CacheService,
  ) { }

  ngOnInit(): void {
    this.listDepartamentos= this.cacheService.getDepartamentos();
  }


  public seleccionar(departamento:Departamento){
    this.enviarPadre(departamento);
  }

    //EVENTO QUE ENVIA EL OBJETO AL PADRE
    private  enviarPadre(value: Departamento){
      this.newItemEvent.emit(value);
    }
    //##############################3

 


}
