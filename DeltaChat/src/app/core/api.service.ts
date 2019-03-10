
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../model/user.model';
import {Observable} from 'rxjs/index';
import {ApiResponse} from '../model/api.response';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Injectable()
export class ApiService {

  constructor(private http: HttpClient) { }
 private baseUrl = 'http://localhost:8080/chat.services/users/';
  private socketUrl = 'http://localhost:8080/chat.services/socket'
  private stompClient;

  login(loginPayload): Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/chat.services/' + 'token/generate-token', loginPayload);
  }

  sendMessage(message) {
    if (message) {
     return this.stompClient.send('http://localhost:8080/chat.services/chat/sendMessage', {}, message);
    }
  }

  initializeWebSocketConnection() {
    const token = window.localStorage.getItem('token');
    let ws = new SockJS(this.socketUrl);
    this.stompClient = Stomp.over(ws);
    var headers = {
      Authorization: token};
    return this.stompClient.connect(headers);
  }

  connect() {
    let ws = new SockJS('/socket');
    this.stompClient = Stomp.over(ws);
    let that = this;
    return this.stompClient.connect({});
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl + 'All');
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createUser(user: User): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl, user);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl + user.id, user);
  }

  deleteUser(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }
}
