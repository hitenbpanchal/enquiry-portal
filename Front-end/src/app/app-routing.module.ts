import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { NoramluserComponent } from './pages/noramluser/noramluser.component';
import { adminGuard } from './services/guard/admin.guard';
import { EnqueryFormComponent } from './pages/enquery-form/enquery-form.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { routerGuard } from './services/router-guard/router.guard';

const routes: Routes = [
  {
    path:'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path:'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path:'admin',
    component:AdminComponent,
    pathMatch:'full',
    canActivate:[adminGuard]
  },
  // {
  //   path:'user',
  //   component:NoramluserComponent,
  //   canActivate:[routerGuard],
  //   children:[
  //     {
  //       path:'profile',
  //       component:ProfileComponent
  //     }
  //   ]
  // },
  {
    path:'profile',
    component:ProfileComponent,
    pathMatch:'full',
    canActivate:[routerGuard]
  },
  {
    path:'enquery',
    component:EnqueryFormComponent,
    canActivate:[routerGuard],
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
