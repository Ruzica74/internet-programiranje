import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  constructor(private http: HttpClient) { }

  getMuzeji(token:string){
    return this.http.get("http://localhost:9000/muzeji/"+token);
  }

  getPosjete(token:string, idKorisnika:number){
    return this.http.get("http://localhost:9000/posjete/"+idKorisnika+"/"+token);
  }
}
