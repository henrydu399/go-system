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
import { AdminRolUsuarioComponent } from './rol-usuario/admin-rol-usuario/admin-rol-usuario.component';
import { EdithRolUsuarioComponent } from './rol-usuario/edith-rol-usuario/edith-rol-usuario.component';




/*
  let id: number = parseInt(params.get('id')!);
          let idRolSistema: number = parseInt(params.get('idRolSistema')!);
          let idSistema: number = parseInt(params.get('idSistema')!);
          let nombreRol: string = params.get('nombreRol')!;
          let idUsuario: number = parseInt(params.get('idUsuario')!);
          let idTipoIdentificacion: number = parseInt(params.get('idTipoIdentificacion')!);
          let numeroIdentificacion: string = params.get('numeroIdentificacion')!;
*/
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
      

      { path: 'adminRolUsuario' , component: AdminRolUsuarioComponent},
      { path: 'editarRolUsuario/:id/:idRolSistema/:idSistema/:nombreRol/:idUsuario/:idTipoIdentificacion/:numeroIdentificacion' , component: EdithRolUsuarioComponent},

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
