import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginForm} from './login-form';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  private _url: string = 'http://localhost:8080'

  constructor(
    private readonly http: HttpClient
  ) { }

  login(data: LoginForm): Observable<LoginForm> {
    return this.http.post<LoginForm>(`${this._url}/login`, data);
  }

}
