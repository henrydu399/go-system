import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { HomeComponent } from './home/home.component';
import { AdminUserComponent } from './user/admin-user/admin-user.component';
import { CreateUserComponent } from './user/create-user/create-user.component';



const routes: Routes = [
  {
    path: 'app',
    component: PagesComponent,
    children: [
      { path: 'adminUser' , component: AdminUserComponent},
      { path: 'createUser' , component: CreateUserComponent},
    
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