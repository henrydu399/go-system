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





@NgModule({
  declarations: [
    PagesComponent,
    AdminUserComponent,
    CreateUserComponent,
    EdithUserComponent,
    NavbarComponent
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
  ],
  providers: [
  ]
})
export class PagesModule { }
