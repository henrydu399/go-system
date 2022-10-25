import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Ciudad } from 'src/app/models/Ciudad';
import { Departamento } from 'src/app/models/Departamento';
import { CacheService } from 'src/app/services/cache.service';
import { HomeService } from 'src/app/services/home.service';
import { MessagesService } from 'src/app/services/menssages.service';
import { UtilHttpService } from 'src/app/utils/utilHttp.service';

@Component({
  selector: 'app-ciudad',
  templateUrl: './ciudad.component.html',
  styleUrls: ['./ciudad.component.css']
})
export class CiudadComponent implements OnInit {

  @Output() newItemEvent = new EventEmitter<Ciudad>();

public listCiudad:Ciudad[] = [];
public listDepartamentos:Departamento[] = [];

public txtBuscarCiudad:string="";

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
  }


  public seleccionar(departamento:Ciudad){
    this.enviarPadre(departamento);
  }

  public getNameDepartamento(idDepartamento : number):string{  
        let value   = this.listDepartamentos.find(elemnt => 
          elemnt.id === idDepartamento );
        if( value === undefined){
          return "";
        }else{
          return value.nombre;
        }
        
  }

    //EVENTO QUE ENVIA EL OBJETO AL PADRE
    private  enviarPadre(value: Ciudad){
      this.newItemEvent.emit(value);
    }
    //##############################3



}
