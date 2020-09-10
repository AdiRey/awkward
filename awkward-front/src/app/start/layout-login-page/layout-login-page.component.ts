import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../sevices/user.service';

@Component({
  selector: 'app-layout-login-page',
  templateUrl: './layout-login-page.component.html',
  styleUrls: ['./layout-login-page.component.css']
})
export class LayoutLoginPageComponent implements OnInit {
  amount: number;

  constructor(private http: HttpClient,
              private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAmountOfUsers()
      .subscribe(
        num => {
          this.amount = num
        }
        , err => {
          console.error(err)
        });
  }
}
