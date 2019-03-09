import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/api.service';
import {Router} from '@angular/router';
import $ from 'jquery';
import {User} from '../model/user.model';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit {
  title = 'Spring Boot WebSocket Chat App';
  constructor(private router: Router, private apiService: ApiService) {
    this.apiService.connect();
  }
  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
  }
  sendMessage(message) {
    this.apiService.sendMessage(message)
      .subscribe( data => {
        $('#input').val('');
      }); }
  }

