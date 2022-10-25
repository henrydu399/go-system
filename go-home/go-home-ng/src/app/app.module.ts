import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PagesModule } from './pages/pages.module';
import { TipoIdentificacionComponent } from './components/tipo-identificacion/tipo-identificacion.component';
import { HeaderComponent } from './Layers/header/header.component';
import { TopbarComponent } from './Layers/topbar/topbar.component';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { HttpClientModule } from '@angular/common/http';
import { MessagesComponent } from './shareds/messages/messages.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthModule } from './auth/auth.module';
import { AuthInterceptor } from './shareds/interceptors/authInterceptos';
import { HomeComponent } from './pages/home/home.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { CiudadComponent } from './modals/ciudad/ciudad.component';
import { BarrioComponent } from './modals/barrio/barrio.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TopbarComponent,
    MessagesComponent,
    HomeComponent,


  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    PagesModule,
    HttpClientModule,
 
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule,

    NgxUiLoaderModule,
    NgxPaginationModule

    
  ],
  exports:[
    NgxUiLoaderModule,
    NgxPaginationModule
  ],
  providers: [AuthInterceptor],
  bootstrap: [AppComponent]
})
export class AppModule { }
