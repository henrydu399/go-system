import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
//componentes propios
import { LoginComponent } from './login/login.component';

import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register-component/register.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { ConfirmarUserComponent } from './confirmar-user/confirmar-user.component';


@NgModule({
  declarations: [LoginComponent, NotFoundComponent, RegisterComponent, ConfirmarUserComponent],
  exports: [LoginComponent, NotFoundComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
 
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxUiLoaderModule
  ],
})
export class AuthModule { }
