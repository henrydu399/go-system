import { NgModule } from '@angular/core';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminUserComponent } from './user/admin-user/admin-user.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { EdithUserComponent } from './user/edith-user/edith-user.component';
import { NavbarComponent } from '../Layers/navbar/navbar.component';
import { FiltroUsuarioPipe } from '../pipes/filtro-usuario.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { AuthInterceptor } from '../shareds/interceptors/authInterceptos';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TipoIdentificacionComponent } from '../modals/tipo-identificacion/tipo-identificacion.component';
import { FiltroTipoIdentificacionPipe } from '../pipes/filtro-tipoIdentificacion.pipe';
import { Departamento } from '../models/Departamento';
import { DepartamentoComponent } from '../modals/departamento/departamento.component';
import { CiudadComponent } from '../modals/ciudad/ciudad.component';
import { BarrioComponent } from '../modals/barrio/barrio.component';
import { FiltroDepartamentoipe } from '../pipes/filtro-departamento.pipe';
import { FiltroCiudadPipe } from '../pipes/filtro-ciudad.pipe';
import { FiltroBarrioPipe } from '../pipes/filtro-barrio';
import { CreatePersonaComponent } from './persona/create-persona/create-persona.component';
import { AdminPersonaComponent } from './persona/admin-persona/admin-persona.component';
import { FiltroPersonaPipe } from '../pipes/filtro-persona';
import { EdithPersonaComponent } from './persona/edith-persona/edith-persona.component';
import { CreateRolUsuarioComponent } from './rol-usuario/create-rol-usuario/create-rol-usuario.component';
import { EdithRolUsuarioComponent } from './rol-usuario/edith-rol-usuario/edith-rol-usuario.component';
import { AdminRolUsuarioComponent } from './rol-usuario/admin-rol-usuario/admin-rol-usuario.component';
import { FiltroRolUsuarioPipe } from '../pipes/filtro-rol-usuario';






@NgModule({
  declarations: [
    PagesComponent,
    AdminUserComponent,
    CreateUserComponent,
    EdithUserComponent,
    NavbarComponent,
    TipoIdentificacionComponent,
    DepartamentoComponent,
    CiudadComponent,
    BarrioComponent,


  
    FiltroCiudadPipe,
    FiltroBarrioPipe,
    FiltroDepartamentoipe,
    FiltroTipoIdentificacionPipe,
    FiltroUsuarioPipe,
    FiltroRolUsuarioPipe,

    CreatePersonaComponent,
    AdminPersonaComponent,
    FiltroPersonaPipe,
    EdithPersonaComponent,
    CreateRolUsuarioComponent,
    EdithRolUsuarioComponent,
    AdminRolUsuarioComponent
  ],
  exports: [
    PagesComponent,


  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,

    NgxUiLoaderModule,
    NgxPaginationModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class PagesModule { }
