import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { LoginRegisterService } from '../login-service.service';
import {LoginForm} from '../login-form';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-page-form',
  templateUrl: './login-page-form.component.html',
  styleUrls: ['./login-page-form.component.css']
})
export class LoginPageFormComponent implements OnInit {
  profileForm: FormGroup;

  constructor(
    private readonly http: HttpClient,
    private readonly fb: FormBuilder,
    private readonly loginRegisterService: LoginRegisterService,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      login: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    console.log(this.profileForm.value);
    let loginForm: LoginForm = {login: this.profileForm.value.login, password: this.profileForm.value.password};
    this.loginRegisterService.login(loginForm)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/'])
            .then(r => console.log(r));
        },
        error => console.error(error)
      );
  }

  isMarkedAsInvalid(controlName: string): boolean {
    const control = this.profileForm.get(controlName);
    return control && control.invalid && (control.dirty || control.touched);
  }

}
