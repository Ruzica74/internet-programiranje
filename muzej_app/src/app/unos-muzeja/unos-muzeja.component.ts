import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Drzava } from '../model/drzava.model';
import { Korisnik } from '../model/korisnik.model';
import { Muzej } from '../model/muzej.model';
import { TipMuzeja } from '../model/tipMuzeja.model';
import { AdminService } from '../servisi/admin.service';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';

@Component({
  selector: 'app-unos-muzeja',
  templateUrl: './unos-muzeja.component.html',
  styleUrls: ['./unos-muzeja.component.css']
})
export class UnosMuzejaComponent implements OnInit {

  public korisnik:Korisnik;
  public muzej:Muzej=new Muzej();
  public selectedRegion="";
  public drzave:Array<Drzava> =new Array<Drzava>();
  public regioni:Array<string> =new Array<string>();
  public gradovi:Array<string> =new Array<string>();
  public tipovi: TipMuzeja[]=[];
  public tip:string="";
  public tipMuzeja: TipMuzeja=new TipMuzeja();
  public code:string="";
  public naziv:string="";
  public brojTelefona:string='';
  public adresa:string='';
  public geoD:number=0;
  public geoS:number=0;
  public grad:string="";
  public drzava:string='';
  public tipM:string='';

  

  constructor(private router : Router, private adminServis : AdminService,
    private servicePodaci : CuvanjePodatakaService) { 
      this.korisnik=new Korisnik(0,"","","","","","",0);
      this.korisnik=servicePodaci.getKorisnik();
      this.adminServis.getTipoveMuzeja(this.korisnik.token).subscribe((result:any)=>{
        console.log(result)
        this.tipovi=result;
      })
      this.adminServis.getDrzave().subscribe((result:any)=>{
        for(var i in result){
          this.drzave.push(new Drzava(result[i].name.common, result[i].cca2));
          
        }
      })

      console.log(this.muzej)
    }

  ngOnInit(): void {
    var a=this.servicePodaci.idAdmin();
    if(this.korisnik==null || a=="false"){
      this.router.navigateByUrl("/");
    }
    (<HTMLInputElement>document.getElementById("labela")).style.display="none";
  }

  public pocetna(){
    this.router.navigateByUrl('admin');
  }

  public uzmiRegion(){
    for(var i in this.drzave){
      if(this.drzave[i].naziv==this.drzava)
      {
        this.code=this.drzave[i].code;
      }
    }
    if(this.code!=""){
      this.regioni.length=0;
      this.adminServis.getRegion(this.code).subscribe((result:any)=>{
        for(var i in result){
          this.regioni.push(result[i].region);
        }
      })
    }
  }

  public uzmiGrad(){
    if(this.selectedRegion!='' && this.code!=''){
      this.gradovi.length=0;
      this.adminServis.getGradove(this.code, this.selectedRegion).subscribe((result:any)=>{
        for(var i in result){
          this.gradovi.push(result[i].city);
        }
        
      })
    }

  }

  public unos(){
    if(this.brojTelefona!='' && this.adresa!='' && this.geoD!=0  && this.geoS!=0  && this.naziv!='' 
        && this.drzava!='' && this.grad!='' && (this.tip!='' || this.tipM!='') && this.geoS!=null && this.geoD!=null
        && this.brojTelefona!=null && this.adresa!=null && this.drzava!=null && this.grad!=null && this.naziv!=null ){
    if(this.tip != ""){
      this.tipMuzeja.id=0;
      this.tipMuzeja.tip=this.tip;
      this.adminServis.insertTip(this.korisnik.token, this.tipMuzeja).subscribe((result:any)=>{
        console.log(result);
        this.muzej.tipMuzeja=this.tipMuzeja;
        
      })  
    }else{
      for(var i in this.tipovi){
        if(this.tipM==this.tipovi[i].tip){
          this.muzej.tipMuzeja=this.tipovi[i];
        }
      }
    }
    
      this.muzej.brojTelefona=this.brojTelefona;
      this.muzej.naziv=this.naziv;
      this.muzej.lokacija.adresa=this.adresa;
      this.muzej.lokacija.drzava=this.drzava;
      this.muzej.lokacija.geolokacijaDuzina=this.geoD;
      this.muzej.lokacija.geolokacijaSirina=this.geoS;
      this.muzej.lokacija.grad=this.grad;

      console.log(this.muzej)
      this.adminServis.insertMuzej(this.korisnik.token, this.muzej).subscribe((result:any)=>{
        console.log(result);
        if(result==1){
          (<HTMLInputElement>document.getElementById("obvj")).innerHTML="Unijeli ste novi muzej";
      (<HTMLInputElement>document.getElementById("labela")).style.display="block";
        }else{
          (<HTMLInputElement>document.getElementById("obvj")).innerHTML="Niste dobro unijeli podatke!";
      (<HTMLInputElement>document.getElementById("labela")).style.display="block";
        }
      })
    }else{
      (<HTMLInputElement>document.getElementById("obvj")).innerHTML="Niste dobro unijeli podatke!";
      (<HTMLInputElement>document.getElementById("labela")).style.display="block";
    }
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
