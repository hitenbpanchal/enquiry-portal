import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginserviceService } from '../login/loginservice.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  

  constructor(private loginService: LoginserviceService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    //add the jwt token from localstorage
    let authReq = request
    const token = this.loginService.getToken();
    console.log("this is auth interceptor: "+token);
    if(token!=null){
      authReq = authReq.clone({
        setHeaders:{"Authorization":`Bearer ${token}`}
      })
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders=[
  {
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true
  }
]
