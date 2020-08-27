import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-image-form',
  templateUrl: './image-form.component.html',
  styleUrls: ['./image-form.component.css']
})
export class ImageFormComponent implements OnInit {
  private readonly AMOUNT_URL = 'http://localhost:8080/api/users/amount';
  amount: number;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
     this.getAmountOfUsers();
  }

  getAmountOfUsers(): void {
    this.http.get<number>(this.AMOUNT_URL)
      .subscribe(num => this.amount = num, error => console.error(error))
  }

}
