import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { RegisterserviceService } from 'src/app/services/registerservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(
    private registerService: RegisterserviceService,
    private router: Router,
    private _snackBar: MatSnackBar
    // public response: Response
  ){}

  public register = {
    email:'',
    password:'',
    // roles:[]
  }

  // public response!: Response[];

  formSubmit(){
    // alert("submit");
    console.log(this.register);
    this.registerService.registerUser(this.register).subscribe(
      (data:any)=>{
        // this.response=data
        console.log(data);

        this._snackBar.open("User Registered Successfully!!!",'OK',{
          duration:5000
        })

        this.router.navigate(['login']);
      },
      (res:any)=>{
        console.log(res);
        this._snackBar.open(res.error.message,'OK',{
          duration:5000
        })
        // alert(res.error.message);
        this.router.navigate(['signup']);
      }   
    )
    
  }
}


