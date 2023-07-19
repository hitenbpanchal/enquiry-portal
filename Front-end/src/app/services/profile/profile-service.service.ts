import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/pages/profile/Profile';
import baserUrl from '../helper';
import enqUrl from '../enqUrl';

@Injectable({
  providedIn: 'root'
})
export class ProfileServiceService {

  constructor(
    private http:HttpClient
  ) { }

  public getProfile(phone:string):Observable<Profile>{
    return this.http.get<Profile>(`${enqUrl}/profile/phoneApi/`+phone);
  }

  public registerProfile(profile:any):Observable<Profile>{
    return this.http.get<Profile>(`${enqUrl}/profile/newProfile`);
  }
}
