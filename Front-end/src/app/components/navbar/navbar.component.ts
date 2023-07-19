import { Component } from '@angular/core';
import { LoginserviceService } from 'src/app/services/login/loginservice.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent {

  constructor(
    
    public loginService:LoginserviceService
    // private profile:ProfileComponent
  ){}

  logOut(){
    this.loginService.logOut();
  }

}
