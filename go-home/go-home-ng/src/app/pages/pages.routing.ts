import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { HomeComponent } from './home/home.component';
import { AdminUserComponent } from './user/admin-user/admin-user.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { EdithUserComponent } from './user/edith-user/edith-user.component';
import { CreatePersonaComponent } from './persona/create-persona/create-persona.component';
import { AdminPersonaComponent } from './persona/admin-persona/admin-persona.component';
import { EdithPersonaComponent } from './persona/edith-persona/edith-persona.component';



const routes: Routes = [
  {
    path: 'app',
    component: PagesComponent,
    children: [
      { path: 'adminUser' , component: AdminUserComponent},
      { path: 'createUser' , component: CreateUserComponent},
      { path: 'editarUser/:idUsuario/:idTipoIdentificacion/:numeroIdentificacion' , component: EdithUserComponent},

      { path: 'adminPersona' , component: AdminPersonaComponent},
      { path: 'createPersona' , component: CreatePersonaComponent},
      { path: 'editarPersona/:idTipoIdentificacion/:numeroIdentificacion' , component: EdithPersonaComponent},
      
         
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
