import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';

import { Korisnik } from '../model/korisnik.model';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';
import { PrijavaService } from '../servisi/prijava.service';
import { RegistracijaService } from '../servisi/registracija.service';



@Component({
  selector: 'app-prva-komponenta',
  templateUrl: './prva-komponenta.component.html',
  styleUrls: ['./prva-komponenta.component.css']
})
export class PrvaKomponentaComponent implements OnInit {
  
 kIme:string="";
 lozinka:string="";
 ime:string="";
 prezime:string="";
 lozinka1:string="";
 mail:string="";
 korisnik: Korisnik;
 admin:number=0;
 kImePrijava:string="";
 lozinkaPrijava:string="";
 imeZauzeto:number=0;
 jedan:number=1;
 hide=true;
 hide1=true;
 hide2=true;
 


  constructor( private sanitizer: DomSanitizer, private servicePrijava: PrijavaService, 
    private router: Router, private serviceRegistracija: RegistracijaService, private servicePodaci: CuvanjePodatakaService) { 
    this.korisnik=new Korisnik(0,"", "", "", "", "", "", 0);
  }

  ngOnInit(): void {
    (<HTMLInputElement>document.getElementById("obav")).style.display="none";
    (<HTMLInputElement>document.getElementById("nalogNapravljen")).style.display="none";
  }

prijavaVidljivo(){
  (<HTMLInputElement>document.getElementById("prijava")).style.display="flex";
  (<HTMLInputElement>document.getElementById("nalog")).style.display="none";
  (<HTMLInputElement>document.getElementById("obav")).style.display="none";
  (<HTMLInputElement>document.getElementById("nalogNapravljen")).style.display="none";
  this.ocisti();
}

nalogVidljivo(){
  (<HTMLInputElement>document.getElementById("prijava")).style.display="none";
  (<HTMLInputElement>document.getElementById("nalog")).style.display="flex";
  (<HTMLInputElement>document.getElementById("obav")).style.display="none";
  (<HTMLInputElement>document.getElementById("nalogNapravljen")).style.display="none";
  this.ocisti();
}
prijava(){
  this.servicePrijava.getKorisnik(this.kImePrijava).subscribe((result:any)=>
    {
      if(result!=null){
      this.korisnik=new Korisnik(result.id, result.ime, result.prezime, result.korisnickoIme, 
        result.lozinka, result.mail, result.token, result.aktivan); 
        console.log(this.korisnik.korisnickoIme);}
        this.provjera();
        
    }
  )
}

ocisti(){
  this.ime="";
  this.prezime="";
  this.kIme="";
  this.kImePrijava="";
  this.lozinka="";
  this.lozinka1="";
  this.mail="";
  this.lozinkaPrijava="";
}

provjera(){
  if(this.kImePrijava==this.korisnik.korisnickoIme && this.lozinkaPrijava==this.korisnik.lozinka){
    this.servicePrijava.getIsAdmin(this.korisnik.id, this.korisnik.token).subscribe((result:any)=>{
      this.admin=0;
      console.log("result"+result);
      if(result!=null){
        this.admin=1;
        console.log("result"+result + "boolean "+this.admin);
      }
      this.rutiranje();
    }); 
  }else {
    (<HTMLInputElement>document.getElementById("obav")).style.display="block";
  }
}

rutiranje(){
  localStorage.setItem("korisnik", JSON.stringify(this.korisnik));
  console.log("token "+this.korisnik.token);
  this.korisnik.aktivan=1;
  console.log("aktivnost "+this.korisnik.aktivan);
  this.servicePrijava.putAktivnost(this.korisnik.token).subscribe((result:any)=>{
    console.log(result);
  });
  if(this.admin==1){
    this.router.navigateByUrl("/admin");
    console.log("ruta admin"+ "boolean "+this.admin);
    localStorage.setItem("admin","true");
    
  }else{
    localStorage.setItem("admin","false");
    this.servicePrijava.updateAktivan(this.korisnik).subscribe((result:any)=>
    {
      console.log("result "+result);
    })
    
    this.router.navigateByUrl("/korisnik");
  }
  
  (<HTMLInputElement>document.getElementById("obav")).style.display="none";
}


napraviNalog(){
  this.serviceRegistracija.getJedinstvenoKorisnickoIme(this.kIme).subscribe((result:any)=>{
     this.imeZauzeto=0;
     console.log("napravi nalog: "+result);
     if(result!=null){
       this.imeZauzeto=1;
     }
     this.noviNalog();
  });
  

}

noviNalog(){
  if(this.imeZauzeto==1){
    (<HTMLInputElement>document.getElementById("obav")).style.display="block";
  }else{
    if(this.lozinka==this.lozinka1){
      (<HTMLInputElement>document.getElementById("obav")).style.display="none";
      this.korisnik=new Korisnik(0,this.ime, this.prezime, this.kIme, this.lozinka, this.mail, "", 0);
      this.serviceRegistracija.insetKorisnik(this.korisnik).subscribe((result:any)=>{
        console.log(result);
      }
      )
      console.log("ime korisnika nalog: "+this.korisnik.ime);
      (<HTMLInputElement>document.getElementById("nalogNapravljen")).style.display="block";
    }else{
      (<HTMLInputElement>document.getElementById("obav")).style.display="block";
    } 

  }
}
 
}


