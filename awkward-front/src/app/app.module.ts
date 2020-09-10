import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { LayoutLoginPageComponent } from './start/layout-login-page/layout-login-page.component';
import { LoginPageFormComponent } from './start/layout-login-page/login-page-form/login-page-form.component';
import { HttpClientModule } from '@angular/common/http';
import { LayoutRegisterPageComponent } from './start/layout-register-page/layout-register-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutLoginPageComponent,
    LoginPageFormComponent,
    LayoutRegisterPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
