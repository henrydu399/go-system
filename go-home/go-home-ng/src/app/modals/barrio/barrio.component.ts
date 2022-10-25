import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Barrio } from 'src/app/models/Barrio';
import { Ciudad } from 'src/app/models/Ciudad';
import { Departamento } from 'src/app/models/Departamento';
import { CacheService } from 'src/app/services/cache.service';
import { HomeService } from 'src/app/services/home.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { TipoIdentificacionService } from 'src/app/services/tipo-identificacion.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-barrio',
  templateUrl: './barrio.component.html',
  styleUrls: ['./barrio.component.css']
})
export class BarrioComponent implements OnInit {

  @Output() newItemEvent = new EventEmitter<Barrio>();

  public listBarrios:Barrio[] = [];
  public listCiudad:Ciudad[] = [];
  public listDepartamentos:Departamento[] = [];
  
  public txtBuscarBarrio:string="";
  
  //TABLA 
  public search: string = "";
  public page: number = 1;
  public total: number = 0;
  //####

  constructor(
    private utilHttpService:UtilHttpService,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
    private cacheService :CacheService ,
  ) { }

  ngOnInit(): void {
    this.listCiudad =  this.cacheService.getCiudades();
    this.listDepartamentos = this.cacheService.getDepartamentos();
    this.listBarrios = this.cacheService.getBarrios();
  }

  public seleccionar(departamento:Barrio){
    this.enviarPadre(departamento);
  }

    //EVENTO QUE ENVIA EL OBJETO AL PADRE
    private  enviarPadre(value: Barrio){
      this.newItemEvent.emit(value);
    }
    //##############################3


    public getNameDepartamento(idDepartamento : number):string{
      let value   = this.listDepartamentos.find(elemnt => 
        elemnt.id === idDepartamento );
      if( value === undefined){
        return "";
      }else{
        return value.nombre;
      }   
}

public getNameCiudad(idCiudad : number):string{
  
  let value   = this.listCiudad.find(elemnt => 
    elemnt.id.id === idCiudad );
  if( value === undefined){
    return "";
  }else{
    return value.nombre;
  }   
}

}
