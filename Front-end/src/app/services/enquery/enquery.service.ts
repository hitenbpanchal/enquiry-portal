import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Enquery } from 'src/app/pages/enquery-form/Enquery';
import baserUrl from '../helper';
import enqUrl from '../enqUrl';

@Injectable({
  providedIn: 'root'
})
export class EnqueryService {

  constructor(private http:HttpClient) { }

  public postEnquery(enquery:Enquery):Observable<Enquery>{
    return this.http.post<Enquery>(`${enqUrl}/forms/enq`,enquery);
  }

  public setEnquery(enquery:any){
    localStorage.setItem("enquery",enquery);
  }

  public getEnquery() : any {
    return localStorage.getItem("enquery");
  }
}
