import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { mensaje } from 'src/app/constants/menssagesConstants';
import { Barrio } from 'src/app/models/Barrio';
import { Ciudad } from 'src/app/models/Ciudad';
import { Departamento } from 'src/app/models/Departamento';
import { HomeService } from 'src/app/services/home.service';
import { MessagesService } from 'src/app/services/menssages.service';

declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit , AfterViewInit {

  public listDepartamentos : Departamento[] = [];
  public listBarrios :Barrio[] = [];
  public listCiudades :Ciudad[] = [];

  

  constructor(
    private homeService :HomeService ,
    private loader: NgxUiLoaderService,
    private msgService: MessagesService,
             ) 
  { }

  AFTERVIEWINIT = "AFTERVIEWINIT";
  ngAfterViewInit(): void {
    this.loader.start(this.AFTERVIEWINIT);
    setTimeout(() => {

      console.log("EJECUTANDO JQ..")
      $('select').selectpicker({
        style: 'btn-select',
        size: 'auto',
        container: 'body',
    });
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent)) {
        $('select').selectpicker('mobile');
    }
    this.loader.stop(this.AFTERVIEWINIT);
    console.log("EJECUTANDO CON EXITO..")

    }, 2000);

  }

  ngOnInit(): void {
    this.getAllDepartamentos();
    this.getAllBarrios();
    this.getAllCiudades();
  }



    //METODO QUE CONSULTA LOS DEPARTAMENTOS
    TASK_GET_ALL_DEPARTAMENTOS = "TASK_GET_ALL_DEPARTAMENTOS";
    private getAllDepartamentos(){
      this.loader.start(this.TASK_GET_ALL_DEPARTAMENTOS);
      this.homeService.getAllDepartamentos().subscribe({
        next: (result) => {        
          this.listDepartamentos = result;  
          this.loader.stop(this.TASK_GET_ALL_DEPARTAMENTOS);
        },
        error: (error) => {
          this.msgService.lanzarAlerta(mensaje.ERROR, error.message ,error.status);
          this.loader.stop(this.TASK_GET_ALL_DEPARTAMENTOS);
        }
      });
    }

        //METODO QUE CONSULTA LAS CIUDADES
        TASK_GET_ALL_CIUDADES = "TASK_GET_ALL_CIUDADES";
        private getAllCiudades(){
          this.loader.start(this.TASK_GET_ALL_CIUDADES);
          this.homeService.getAllCiudades().subscribe({
            next: (result) => {        
              this.listCiudades = result;  
              this.loader.stop(this.TASK_GET_ALL_CIUDADES);
            },
            error: (error) => {
              this.msgService.lanzarAlerta(mensaje.ERROR, error.message ,error.status);
              this.loader.stop(this.TASK_GET_ALL_CIUDADES);
            }
          });
        }


        //METODO QUE CONSULTA LOS BARRIOS
        TASK_GET_BARRIOS = "TASK_GET_BARRIOS";
        private getAllBarrios(){
       
          console.log("CARGANDO BARRIOS..")
          this.loader.start(this.TASK_GET_BARRIOS);
          this.homeService.getAllBarrios().subscribe({
            next: (result) => {     
              this.listBarrios = result; 
              this.loader.stop(this.TASK_GET_BARRIOS);
              console.log(" BARRIOS CARGADOS CORRECTAMENTE")             
            },
            error: (error) => {
              this.msgService.lanzarAlerta(mensaje.ERROR, error.message ,error.status);
              this.loader.stop(this.TASK_GET_BARRIOS);
              console.log(" ERROR CARGANDO CORRECTAMENTE")
            }
          });
        }

}
