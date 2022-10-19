import { NgModule } from '@angular/core';
import { RouterModule, Routes  } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register-component/register.component';
import { HomeComponent } from './pages/home/home.component';
import { PagesRoutingModule } from './pages/pages.routing';

const ROUTES : Routes = [ 
  { path: 'productos' , component: HomeComponent}, 
  { path: 'login' , component: LoginComponent},
  { path: 'register' , component: RegisterComponent},
  { path: '' , redirectTo: '/productos', pathMatch:'full'} ,
]

@NgModule({
  imports: [RouterModule.forRoot(ROUTES,{ useHash: true }) , PagesRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
