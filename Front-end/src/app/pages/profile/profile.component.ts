import { Component } from '@angular/core';
import { Profile } from './Profile';
import { ProfileServiceService } from 'src/app/services/profile/profile-service.service';
import { EnqueryService } from 'src/app/services/enquery/enquery.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {


  getProfile:Profile= new Profile;
  profile:Profile = new Profile;
  

  constructor(
    private profileService:ProfileServiceService,
  ){ }

  ngOnInit(): void{

    const phone = localStorage.getItem("phone");
    //get the profile data
    this.getProfileData(phone)
  }

  public registerProfile(profile:Profile){
    this.profileService.registerProfile(profile).subscribe(
      (data)=>{
        this.getProfile=data
        console.log(data);
      },
      (error)=>{
        console.log(error);
        console.log("Profile Post Error");
      }
    )
  }

  public getProfileData(phone:any){
    this.profileService.getProfile(phone).subscribe(
      (data)=>{
        // console.log(data);
        this.getProfile=data
        console.log("This Is profile Data works");
        console.log(this.profile);
      },
      (error)=>{
        console.log(error);
      }
    )
  }

}
