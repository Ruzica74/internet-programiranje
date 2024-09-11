import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { Router } from '@angular/router';
import { Korisnik } from '../model/korisnik.model';
import { Muzej } from '../model/muzej.model';
import { Posjeta } from '../model/posjeta.model';
import { AdminService } from '../servisi/admin.service';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';
import { ImageServiceService } from '../servisi/image-service.service';
import {DomSanitizer} from '@angular/platform-browser';


class ImageSnippet {
  constructor(public src: string, public file: File) {}
}

@Component({
  selector: 'app-kreiranje-posjeta',
  templateUrl: './kreiranje-posjeta.component.html',
  styleUrls: ['./kreiranje-posjeta.component.css']
})
export class KreiranjePosjetaComponent implements OnInit {
  public korisnik:Korisnik;
  selectedFile: ImageSnippet | undefined;
  public broj:number=0;
  public muzeji:Muzej[]=[];
  public datum:Date | undefined;
  public vrijeme: string | undefined;
  public trajanje: number |undefined;
  public cijena: number | undefined;
  public slike: string[]=[];
  public video:string='';
  public selectedMuzej : string | undefined;
  posjeta :Posjeta=new Posjeta();
  idPrezentacije:number=0;
  public ytVideo:string ='';
  public minDate: Date=new Date();
  public ytZaPrikaz: string='';
  public yt: string='';
  public vrj:string='';
  public videoSaRacunara: string='';
  public inputSLika:string='';

  constructor(private router:Router, private adminService: AdminService,
     private servicePodaci:CuvanjePodatakaService, private imageService: ImageServiceService,
     private sanitizer: DomSanitizer) { 
      this.korisnik=new Korisnik(0,"","","","","","",0);
      this.korisnik=servicePodaci.getKorisnik();
      this.adminService.getMuzeji(this.korisnik.token).subscribe((result:any)=>{
        this.muzeji=result;
      })
      var danas=new Date();
      this.minDate.setDate(danas.getDate()+1);
      console.log(this.minDate);
    }

  ngOnInit(): void {
    var a=this.servicePodaci.idAdmin();
    if(this.korisnik==null || a=="false"){
      this.router.navigateByUrl("/");
    }
    this.slike.length=0;
    (<HTMLInputElement>document.getElementById("obvj")).style.display='none';
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();
    this.yt='false';
    console.log(file.name);
    this.broj=this.broj+1;
    reader.addEventListener('load', (event: any) => {

      this.selectedFile = new ImageSnippet(event.target.result, file);

      this.imageService.uploadImage(this.selectedFile.file, this.korisnik.token).subscribe((result:any)=>{
        this.slike.push(result);
        
      })
        
    });

    reader.readAsDataURL(file);
  }

  ytPromjena(){
    (<HTMLInputElement>document.getElementById("yt")).disabled=true;
    (<HTMLInputElement>document.getElementById("upload")).disabled=true;
    (<HTMLInputElement>document.getElementById("player")).style.display='block';
    this.yt='true';
    this.ytZaPrikaz='https://www.youtube.com/embed/' +this.ytVideo.substring(32);
    console.log(this.ytZaPrikaz);
    this.posjeta.prezentacijaByPrezentacijaId.video=this.ytZaPrikaz;
  }

  getVideoForYt(){

    return this.sanitizer.bypassSecurityTrustResourceUrl(this.ytZaPrikaz);
  }

  processVideo(videoInput:any){
    const file: File = videoInput.files[0];
    const reader = new FileReader();
    
    console.log(file.name);
    (<HTMLInputElement>document.getElementById("yt")).disabled=true;
    (<HTMLInputElement>document.getElementById("upload")).disabled=true;
    (<HTMLInputElement>document.getElementById("videoUpload")).style.display='block';
    
    reader.addEventListener('load', (event: any) => {

      

      this.imageService.uploadImage(file, this.korisnik.token).subscribe((result:any)=>{
        this.video=result;
        var putanja=result;
        console.log(putanja);
        this.posjeta.prezentacijaByPrezentacijaId.video=this.video;
      })
        
    });

    reader.readAsDataURL(file);
    
  }

  public sacuvaj(){
    if(this.datum!=null && this.vrijeme!=null && this.broj>4 && this.cijena!= null && this.posjeta.prezentacijaByPrezentacijaId.video!=''
    && this.selectedMuzej!=null && this.trajanje!=null  && this.vrijeme!='' && this.selectedMuzej!='')
    {
      this.posjeta.cijena=this.cijena;
      this.posjeta.datum=this.datum;
      this.posjeta.trajanje=this.trajanje;
      console.log(this.vrj);
      
      for(var m in this.muzeji){
        if(this.selectedMuzej==this.muzeji[m].naziv)
          this.posjeta.prezentacijaByPrezentacijaId.muzejByMuzejId=this.muzeji[m];
      }

      this.adminService.insertPosjeta(this.korisnik.token,this.posjeta, this.vrijeme).subscribe((result:any)=>{
        this.idPrezentacije=result;
        console.log("id prezentacije "+this.idPrezentacije);
        
        if(result!=0 && result!=null){
          this.adminService.insertSlike(this.korisnik.token, this.idPrezentacije, this.slike).subscribe((result:any)=>{
            console.log(result);
            if(result==1){
              (<HTMLInputElement>document.getElementById("labela")).innerHTML='Unijeli ste novi muzej';
              (<HTMLInputElement>document.getElementById("obvj")).style.display='block';
            }
          })
        }else{
          (<HTMLInputElement>document.getElementById("labela")).innerHTML='Niste dobro unijeli podatke';
          (<HTMLInputElement>document.getElementById("obvj")).style.display='block';
        }
      })
    }else{
      (<HTMLInputElement>document.getElementById("labela")).innerHTML='Niste dobro unijeli podatke';
      (<HTMLInputElement>document.getElementById("obvj")).style.display='block';
    }
    this.broj=0;
    this.slike.length=0;
    this.video='';
    this.videoSaRacunara='';
    this.yt='';
    this.ytVideo='';
    this.selectedMuzej='';
    this.inputSLika='';
    //this.cijena=undefined;
    this.datum=undefined;
    //this.trajanje=undefined;
    //this.vrijeme='';
    (<HTMLInputElement>document.getElementById("yt")).disabled=false;
    (<HTMLInputElement>document.getElementById("upload")).disabled=false;
    (<HTMLInputElement>document.getElementById("slike")).disabled=false;
    (<HTMLInputElement>document.getElementById("player")).style.display='none';
    (<HTMLInputElement>document.getElementById("videoUpload")).style.display='none';

  }

  public pocetna(){
    this.router.navigateByUrl('admin');
  }

  radSaKorisnicima(){
    this.router.navigateByUrl('radSaKorisnicima');
  }
  unosMuzeja(){
    this.router.navigateByUrl('unosMuzeja');
  }

  kreiranjePosjeta(){
    this.router.navigateByUrl('kreiranjePosjeta');
  }

  pregledLogova(){
    this.router.navigateByUrl('pregledLogova')
  }

}
