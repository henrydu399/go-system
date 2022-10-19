import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { HomeComponent } from './home/home.component';
import { AdminUserComponent } from './user/admin-user/admin-user.component';



const routes: Routes = [
  {
    path: 'app',
    component: PagesComponent,
    children: [
      { path: 'adminUser' , component: AdminUserComponent},
      
    
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
