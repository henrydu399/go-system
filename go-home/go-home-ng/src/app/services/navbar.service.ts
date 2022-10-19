import { Injectable } from "@angular/core";
import { NgxUiLoaderService } from "ngx-ui-loader";

@Injectable({
    providedIn: 'root'
  })
  export class NavbarService {

    private  TITLE_KEY:string = 'parameters-title';
  
   public title:string = "";
  
    constructor(private loader: NgxUiLoaderService) { }
  
    public changeTitle(value:string){
      this.title = value;
      this.saveTitle(value);
    }



  public saveTitle(title: string) {
    window.sessionStorage.removeItem(this.TITLE_KEY);
    window.sessionStorage.setItem(this.TITLE_KEY, title);
  }

  public getTitle(): string {
    return sessionStorage.getItem(this.TITLE_KEY)!;
  }

  }