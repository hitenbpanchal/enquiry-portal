import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from '../helper';
import { SignupComponent } from 'src/app/pages/signup/signup.component';
import { Observable } from 'rxjs';
import { LoginResponse } from 'src/app/pages/login/LoginResponse';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

  constructor(
    private http: HttpClient,
    public router:Router
  ) { }

  public generateToken(loginReq: any):Observable<LoginResponse>{
   return this.http.post<LoginResponse>(`${baserUrl}/login`,loginReq);
  }

  public getCurrentUser() : any {
    return this.http.get(`${baserUrl}/login/current-user`);
  }

  public loginUser(token: string){
    localStorage.setItem("token",token);
    return true;
  }

  //user is logged in or not
  public isLoggedIn(){
    let tokenStr = localStorage.getItem("token");
    if(tokenStr==undefined || tokenStr == '' || tokenStr == null){
      return false
    }else{
      return true
    }
  }

  //logout function
  public logOut(){
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    this.router.navigate(['login'])
    return true;
  }

  //get Token
  public getToken(){
    return localStorage.getItem("token");
  }

  //To set Userdetails
  public setUser(user: any){
    localStorage.setItem('user',JSON.stringify(user));
  }

  //to get user
  public getUser(){
    let userStr = localStorage.getItem("user");
    if(userStr!=null){
      return JSON.parse(userStr);
    }else{
      this.logOut()
      return null;
    }
  }

  //get user role
  public getUserRole(){
    let user = this.getUser()
    return user.authorities[0].authority;
  }


}
