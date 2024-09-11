import { Injectable } from '@angular/core';

import { Korisnik } from '../model/korisnik.model';
import { Muzej } from '../model/muzej.model';

@Injectable({
  providedIn: 'root'
})
export class CuvanjePodatakaService {
  public korisnik:Korisnik;
  public str:string="";
  public muzej: Muzej;
  constructor() { 
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.muzej=new Muzej();
  }

  public getKorisnik(){
    var m=localStorage.getItem("korisnik");
    this.korisnik=m!=null ? JSON.parse(m) : null;
    return this.korisnik;
  }

  public getMuzej(){
    var m=localStorage.getItem("muzej");
    this.muzej=m!=null ? JSON.parse(m) : null;
    return this.muzej;
  }

  public idAdmin(){
    var m=localStorage.getItem("admin");
    if(m!=null && m=="true")
     {return "true";}
     else {
       return "false";
     }
  }
}
