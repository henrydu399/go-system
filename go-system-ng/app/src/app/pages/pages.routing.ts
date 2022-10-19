import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {
    path: 'menu',
    component: PagesComponent,
    children: [
      { path: 'home' , component: HomeComponent},
    
    ],
    canActivate:[],
    canActivateChild:[]
  },
 
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule { }
