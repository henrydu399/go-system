import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SidebarService } from 'src/app/services/sidebar.service';
import { URLS_PERMITIDAS } from 'src/app/shared/commons/urls.permitidas.json';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivateChild {

  constructor(private sidebarService: SidebarService,
    private router: Router) {
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    var lstMenus = this.sidebarService.menusTemporal;
    if (lstMenus == null || lstMenus.length == 0) {
      lstMenus = JSON.parse(localStorage.getItem("menusTemporal") || '{}');
    }

    let urlActual = childRoute.routeConfig?.path;
    if (urlActual == 'not-found') {
      return true;
    }

    if (lstMenus == null || lstMenus.length == 0) {
      this.redireccionar();
      return false;
    }

    let conPrivilegio = false;
    lstMenus.forEach(menu => {
      if (menu.url != null && menu.url == urlActual) {
        conPrivilegio = true;
      }
      var lstSubmenus = menu.submenus;
      lstSubmenus?.forEach(submenu => {
        if (submenu.url == urlActual) {
          conPrivilegio = true;
          this.sidebarService.tituloMenuActual = submenu.descripcion;
        }
      })
    });

    if (!conPrivilegio && URLS_PERMITIDAS.includes(urlActual)) {
      conPrivilegio = true;
    }

    if (!conPrivilegio) {
      this.redireccionar();
    }

    return conPrivilegio;
  }


  redireccionar() {
    this.router.navigate(['/menu/not-found']);
  }
}
