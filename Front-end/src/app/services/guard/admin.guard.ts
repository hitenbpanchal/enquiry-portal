import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginserviceService } from '../login/loginservice.service';

export const adminGuard: CanActivateFn = (route, state) => {

  if(inject(LoginserviceService).isLoggedIn() && inject(LoginserviceService).getUserRole()=="ROLE_ADMIN"){
    return true
  }
  inject(Router).navigate(['login']);
  return false
};
