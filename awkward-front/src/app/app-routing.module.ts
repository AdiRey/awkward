import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutLoginPageComponent } from './layout-login-page/layout-login-page.component';
import {LayoutRegisterPageComponent} from './layout-register-page/layout-register-page.component';


const routes: Routes = [
  { path: 'login', component: LayoutLoginPageComponent },
  { path: 'register', component: LayoutRegisterPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
