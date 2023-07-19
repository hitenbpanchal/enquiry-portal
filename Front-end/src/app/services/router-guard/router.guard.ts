import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginserviceService } from '../login/loginservice.service';

export const routerGuard: CanActivateFn = (route, state) => {
  if(inject(LoginserviceService).isLoggedIn()){
    return true
  }
  inject(Router).navigate(['login']);
  return false;
};
