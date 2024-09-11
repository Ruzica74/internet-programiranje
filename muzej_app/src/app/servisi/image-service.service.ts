import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';


@Injectable({
  providedIn: 'root'
})
export class ImageServiceService {

  constructor(private http:HttpClient) { }

  public uploadImage(image: File, token:string): Observable<Object>{
    const formData = new FormData();

    formData.append('file', image);

    return this.http.post('http://localhost:9000/posjeta/video/'+token, formData,{responseType:'text'});
  }
}
