import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { Departamento } from '../models/Departamento';

@Injectable({
  providedIn: 'root'
})
export class UtilTableService {

  constructor(private http: HttpClient) {
  }

  /**
   * @info Metodo que permite extraer el nombre de las columnas para realizar un filtro por la columna seleccionada
   * @param data 
   */
  public getColunmNames(data:any){


  }
  
 
}