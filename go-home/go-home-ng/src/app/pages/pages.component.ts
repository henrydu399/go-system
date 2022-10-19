import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styles: []
})
export class PagesComponent implements OnInit {
  idleState = "NOT_STARTED";
  countdown?: number | any = null;
  lastPing?: Date | any = null;
  idleSeconds: any;
  idleSecondsTimeout: any;

  constructor() {
 
  }

  ngOnInit(): void {
   
  }

  reset() {
   
  }

}
