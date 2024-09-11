import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Muzej } from '../model/muzej.model';
import { Posjeta } from '../model/posjeta.model';
import { TipMuzeja } from '../model/tipMuzeja.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private lozinka:string="";
  private key:string="6883a63c1cf772ea1a32a4609802eba0";
  
  constructor(private http:HttpClient) { }

  public getBrojRegistrovanih(token: string){
    return this.http.get("http://localhost:9000/aktivnost/registrovani/"+token);
  }

  public getBrojTrenutnoAktivnih(token: string){
    return this.http.get("http://localhost:9000/aktivnost/aktivni/"+token);
  }

  public getBrojAktivnihPoSatima(token: string){
    return this.http.get("http://localhost:9000/aktivnost/"+token);
  }

  public getRegKorisnike(token :string){
    return this.http.get("http://localhost:9000/registrovani/"+token);
  }

  public updateOdobriNalog(token:string, kIme:string){
     return this.http.put("http://localhost:9000/registrovani/azuriraj/"+kIme+"/"+token, "odobri")
  }

  public updateBlokirajNalog(token:string, kIme:string){
    return this.http.put("http://localhost:9000/registrovani/azuriraj/"+kIme+"/"+token, "blokiraj")
  }

  public updateResetujLozinku(token:string, kIme:string){
    this.lozinka="";
    let possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnoprtstqtuvwzy";
    this.lozinka=this.makeRandom(16, possible);
    return this.http.put("http://localhost:9000/registrovani/azuriraj/"+kIme+"/"+token, this.lozinka);
  }

  makeRandom(lengthOfCode: number, possible: string) {
    let text = "";
    for (let i = 0; i < lengthOfCode; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
      return text;
  }

  public getLogove(token :string){
    return this.http.get("http://localhost:9000/logovanje/"+token);
  }

  public getTipoveMuzeja(token: string){
    return this.http.get("http://localhost:9000/muzeji/tip/"+token);
  }

  public insertTip(token:string, tip:TipMuzeja){
    return this.http.post("http://localhost:9000/muzeji/tip/"+token, tip);
  }

  public insertMuzej(token:string, muzej:Muzej){
    return this.http.post("http://localhost:9000/muzeji/"+token, muzej);
  }

  public getDrzave(){
    return this.http.get("https://restcountries.com/v3.1/region/europe");
  }

  public getRegion(code:string){
    return this.http.get("http://battuta.medunes.net/api/region/"+code+"/all/?key="+this.key);
  }

  public getGradove(code:string, region:string){
    return this.http.get("https://battuta.medunes.net/api/city/"+code+"/search/?region="+region+"&key="+this.key)
  }

  public getMuzeji(token:string){
    return this.http.get("http://localhost:9000/muzeji/"+token);
  }

  public insertPosjeta(token: string, data: Posjeta, vrijeme:string){
    let time=new HttpParams();
    console.log(vrijeme);
    time=time.set('time', vrijeme);
    console.log(time.get('time'));
    return this.http.post("http://localhost:9000/posjeta/"+token, data, {params:time});
  }

  public insertSlike(token: string, idPrez:number,data: string[]){
    return this.http.post("http://localhost:9000/posjeta/"+idPrez+"/"+token, data);
  }
}
