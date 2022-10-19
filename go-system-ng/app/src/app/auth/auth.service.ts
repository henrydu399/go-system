import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  lstHeaders = { headers: new HttpHeaders() }
  private _tokenInicioSesion: any | string;

  constructor(private http: HttpClient,
    private router: Router,
    private loader: NgxUiLoaderService,
  ) { }
 




}
