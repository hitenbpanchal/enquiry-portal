import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginserviceService } from 'src/app/services/login/loginservice.service';
import { LoginResponse } from './LoginResponse';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginResponse:LoginResponse = new LoginResponse;

  constructor(
    private loginService: LoginserviceService,
    private router: Router,
    private snackbar:MatSnackBar
  ){}

  public loginReq = {
    username:'',
    password:''
  }

  loginSubmit(){
    console.log("login button works!!");
    this.loginService.generateToken(this.loginReq).subscribe(
      (data: any)=>{
        console.log(data);
        this.loginResponse=data

        this.snackbar.open("User logged in Successfully!!!","OK",{
          duration:5000
        })

        //login user
        this.loginService.loginUser(data.token);

        this.loginService.getCurrentUser().subscribe(
          (user: any)=>{
            this.loginService.setUser(user);
            console.log(user);
            //redirect ....ADMIN: admin dashboard
            //redirect ....STUDENT: student dashboard
            if(this.loginService.getUserRole()=="ROLE_ADMIN"){
              //admin dashboard
              // window.location.href='/admin'
              this.router.navigate(['admin']);
            }else if(this.loginService.getUserRole()=="ROLE_STUDENT"){
              //NORMAL DASHBOARD
              // window.location.href='/user'
              this.router.navigate(['enquery']);
            }else{
              this.loginService.logOut();
            }
          },
          (res:any)=>{
            console.log(res);
            this.snackbar.open(res.error,"OK",{
              duration:5000
            })
          }
        )
      },
      (res: any)=>{
        console.log(res);
        // alert("Something went wrong!!")
        this.snackbar.open(res.error.UnauthorizedRequest,"OK",{
          duration:5000
        })
      }
    )
  }

}
