import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { StartComponent } from './start.component';
import {LayoutLoginPageComponent} from './layout-login-page/layout-login-page.component';
import {LayoutRegisterPageComponent} from './layout-register-page/layout-register-page.component';

const routes: Routes = [
  { path: '', component: StartComponent },
  { path: 'login', component: LayoutLoginPageComponent},
  { path: 'register', component: LayoutRegisterPageComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StartRoutingModule { }
