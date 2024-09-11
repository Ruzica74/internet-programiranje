import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Korisnik } from '../model/korisnik.model';

@Injectable({
  providedIn: 'root'
})
export class PrijavaService {
  public date:Date =new Date();

  constructor(private http:HttpClient, private datepipe:DatePipe) { }

  public getKorisnik(kIme:string){
    return this.http.get("http://localhost:9000/korisnik/kIme/"+kIme+"/token123");
  }

  public getIsAdmin(id:number, token:string){
    return this.http.get("http://localhost:9000/admin/"+id+"/"+token);
  }

  public updateAktivan(korisnik: Korisnik){
    return this.http.put("http://localhost:9000/korisnik/aktivnost/"+korisnik.token, korisnik);
  }

  public putAktivnost(token : string){
    return this.http.post("http://localhost:9000/logovanje/"+token, "Prijava");
  }

}


