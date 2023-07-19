import { Component } from '@angular/core';
import { Enquery } from './Enquery';
import { EnqueryService } from 'src/app/services/enquery/enquery.service';
import { ProfileComponent } from '../profile/profile.component';
import { Router } from '@angular/router';
import { ProfileServiceService } from 'src/app/services/profile/profile-service.service';
import { Profile } from '../profile/Profile';

@Component({
  selector: 'app-enquery-form',
  templateUrl: './enquery-form.component.html',
  styleUrls: ['./enquery-form.component.css']
})
export class EnqueryFormComponent {

  constructor(
    private enqueryService:EnqueryService,
    private router: Router,
    private profileService:ProfileServiceService
    // private profile:ProfileComponent
  ){}

  enquery: Enquery = new Enquery;
  postProfile:Profile = new Profile;
  phoneNo!: string;

  enquerySubmit(){
    console.log("enquery works");
    this.enqueryService.postEnquery(this.enquery).subscribe(
      (data)=>{
        // console.log(data);
        this.enquery=data
        console.log(data);

        //set enqery to localstorege
        this.enqueryService.setEnquery(data);

        //set localstorege data
        this.phoneNo=data.contactNo
        
        localStorage.setItem("phone",this.phoneNo);
        
        this.router.navigate(['/profile'])
        // this.profile.getProfileData(this.enquery.contactNo)
      },
      (error)=>{
        console.log(error);
      }
    );
  }

}
