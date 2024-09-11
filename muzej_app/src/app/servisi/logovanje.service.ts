import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LogovanjeService {

  constructor(private http : HttpClient) {}


  public log(aktivnost :string, token : string){
    return this.http.post("http://localhost:9000/logovanje/"+token, aktivnost)
  }
}
