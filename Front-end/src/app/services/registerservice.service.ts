import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterserviceService {

  constructor(
    private http: HttpClient
  ) { }

  //to Register user
  public registerUser(register: any) : Observable<Response>{
   return this.http.post<Response>(`${baserUrl}/register/new`,register)
  }

}
