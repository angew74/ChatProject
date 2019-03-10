import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/api.service';
import {Router} from '@angular/router';
import $ from 'jquery';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit {
  title = 'Spring Boot WebSocket Chat App';
  private socketUrl = 'http://localhost:8080/chat.services/socket';
  private messageUrl = 'http://localhost:8080/chat.services'

  private stompClient;
  constructor(private http: HttpClient, private router: Router, private apiService: ApiService) {
  //  this.apiService.connect();
    this.initializeWebSocketConnection();
  }
  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
  }

  initializeWebSocketConnection() {
    const token = window.localStorage.getItem('token');
    const ws = new SockJS(this.socketUrl);
    const headers = {
      Authorization: token
    };
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect(headers, function(frame) {
      that.stompClient.subscribe(this.socketUrl + '/chat', (message) => {
        if (message.body) {
          $('.chat').append('<div class="message">' + message.body + '</div>')
          console.log(message.body);
        }
      });
    });
  }

  sendMessage(message) {
    this.stompClient.send('/app/send/message' , {}, message);
    $('#input').val('');
  }}

