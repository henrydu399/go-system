import { NgModule } from '@angular/core';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FooterComponent } from '../Layers/footer/footer.component';
import { HeaderComponent } from '../Layers/header/header.component';
import { HomeComponent } from './home/home.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';



@NgModule({
  declarations: [
    PagesComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    SidebarComponent,
    TopbarComponent
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
