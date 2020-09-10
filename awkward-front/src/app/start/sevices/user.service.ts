import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginForm} from './login-form';
import {Observable} from 'rxjs';
import {RegisterForm} from './register-form';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _url: string = 'http://localhost:8080'
  private _userPrefix: string = "api/users";

  private amount: number;

  constructor(
    private readonly http: HttpClient
  ) { }

  login(data: LoginForm): Observable<LoginForm> {
    return this.http.post<LoginForm>(`${this._url}/login`, data);
  }

  getAmountOfUsers(): Observable<number> {
    return this.http.get<number>(`${this._url}/${this._userPrefix}/amount`);
  }

  register(data: RegisterForm): Observable<RegisterForm>  {
    return this.http.post<RegisterForm>(`${this._url}/${this._userPrefix}`, data);
  }

}
