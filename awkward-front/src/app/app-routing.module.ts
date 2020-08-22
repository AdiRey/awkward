import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutLoginPageComponent } from './layout-login-page/layout-login-page.component';


const routes: Routes = [
  { path: 'login', component: LayoutLoginPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
