import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { MenuService } from 'src/app/services/menu.service';
import { NavbarService } from 'src/app/services/navbar.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: []
})
export class HeaderComponent implements OnInit {

  constructor(public menuService : MenuService , public authService:AuthService , public navbarService:NavbarService) { }

  ngOnInit(): void {
  }

  logout() {
   
  }

}
