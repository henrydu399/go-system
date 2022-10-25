import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { lstHeaders } from '../constants/httpConstants';
import { mensaje } from '../constants/menssagesConstants';
import { Barrio } from '../models/Barrio';
import { Ciudad } from '../models/Ciudad';
import { Departamento } from '../models/Departamento';
import { HomeService } from './home.service';
import { MessagesService } from './menssages.service';

@Injectable({
    providedIn: 'root'
})
export class CacheService {


    private listDepartamentos :Departamento[] = [];

    private CIUDAD_KEY: string = 'ciudad-key';
    private DEPARTAMENTO_KEY: string = 'departamento-key';
    private BARRIO_KEY: string = 'barrio-key';






    constructor(
        private http: HttpClient,
        private homeService: HomeService,
        private loader: NgxUiLoaderService,
        private msgService: MessagesService,
    ) {
    }




    public saveDepartamentos(list :Departamento[]):void{
        window.sessionStorage.removeItem(this.DEPARTAMENTO_KEY);
        window.sessionStorage.setItem(this.DEPARTAMENTO_KEY, JSON.stringify(list));
    }

    public getDepartamentos(): Departamento[] {
            let s :string  = window.sessionStorage.getItem(this.DEPARTAMENTO_KEY)!;
            let u: Departamento[] = JSON.parse(s);
            return u;
    }

    public saveCiudades(list :Ciudad[]):void{
        window.sessionStorage.removeItem(this.CIUDAD_KEY);
        window.sessionStorage.setItem(this.CIUDAD_KEY, JSON.stringify(list));
    }

    public getCiudades(): Ciudad[] {
            let s :string  = window.sessionStorage.getItem(this.CIUDAD_KEY)!;
            let u: Ciudad[] = JSON.parse(s);
            return u;
    }


    public saveBarrios(list :Barrio[]):void{
        window.sessionStorage.removeItem(this.BARRIO_KEY);
        window.sessionStorage.setItem(this.BARRIO_KEY, JSON.stringify(list));
    }

    public getBarrios(): Barrio[] {
            let s :string  = window.sessionStorage.getItem(this.BARRIO_KEY)!;
            let u: Barrio[] = JSON.parse(s);
            return u;
    }

    





}