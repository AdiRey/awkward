import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { LoginFormComponent } from './layout/login-form/login-form.component';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { LayoutLoginPageComponent } from './layout-login-page/layout-login-page.component';
import { LoginPageFormComponent } from './layout-login-page/login-page-form/login-page-form.component';
import { ImageFormComponent } from './layout-login-page/image-form/image-form.component';
import { HttpClientModule } from '@angular/common/http';
import { LayoutRegisterPageComponent } from './layout-register-page/layout-register-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    NavbarComponent,
    LoginFormComponent,
    LayoutLoginPageComponent,
    LoginPageFormComponent,
    ImageFormComponent,
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
