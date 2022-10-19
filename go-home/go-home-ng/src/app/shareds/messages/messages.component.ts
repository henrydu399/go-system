import { Component, OnInit } from '@angular/core';
import { MessagesService } from 'src/app/services/menssages.service';

import { Messages } from './messages.models';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(public messagesService: MessagesService) {
  }

  ngOnInit(): void {
  }
 

   closeAlert(m : Messages) {
    this.messagesService.eliminarMensaje(m);
  
  }

}
