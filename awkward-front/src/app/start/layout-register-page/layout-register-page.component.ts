import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DESCRIPTION_REGEX, EMAIL_REGEX, NAME_AND_SURNAME_REGEX, PASSWORD_REGEX, USERNAME_REGEX} from '../validator/validators';
import {UserService} from '../sevices/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-layout-register-page',
  templateUrl: './layout-register-page.component.html',
  styleUrls: ['./layout-register-page.component.css']
})
export class LayoutRegisterPageComponent implements OnInit {

  profileForm: FormGroup;
  private json: any;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      username: ['', [Validators.required, Validators.pattern(USERNAME_REGEX)]],
      email: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      nameAndSurname: ['', [Validators.required, Validators.pattern(NAME_AND_SURNAME_REGEX)]],
      dateOfBirth: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.pattern(PASSWORD_REGEX)]],
      description: ['', [Validators.pattern(DESCRIPTION_REGEX)]],
      genderId: ['', [Validators.required]]
    })
  }

  isMarkedAsInvalid(controlName: string): boolean {
    const control = this.profileForm.get(controlName);
    return control && control.invalid && (control.dirty || control.touched);
  }

  onSubmit(): void {
    this.json = this.profileForm.value;
    this.json.name = this.profileForm.value.nameAndSurname.split(' ')[0];
    this.json.surname = this.profileForm.value.nameAndSurname.split(' ')[1];
    delete this.json.nameAndSurname;
    console.log(this.json);
    this.userService.register(this.json)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/start/login'])
            .then(r => console.log(r))
        },
        err => {
          console.error(err)
        }
      )
  }

}
