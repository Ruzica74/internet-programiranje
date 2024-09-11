import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Korisnik } from '../model/korisnik.model';

@Injectable({
  providedIn: 'root'
})
export class RegistracijaService {

  constructor(private http:HttpClient) { }

  public getJedinstvenoKorisnickoIme(ime : string){
    return this.http.get("http://localhost:9000/korisnik/provjera/"+ime+"/token123");
  }

  public insetKorisnik(kor :Korisnik){
    return this.http.post("http://localhost:9000/korisnik/token123", kor);
  }
}
