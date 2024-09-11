import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

import { Korisnik } from '../model/korisnik.model';
import { regKorisnik } from '../model/regKorisnik.model';
import { AdminService } from '../servisi/admin.service';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';

export interface Element1 {
  ime: string;
  prezime: string;
  nalog: string;
  korisnickoIme: string;
  mail: string;
}

@Component({
  selector: 'app-rad-sa-korisnicima',
  templateUrl: './rad-sa-korisnicima.component.html',
  styleUrls: ['./rad-sa-korisnicima.component.css']
})
export class RadSaKorisnicimaComponent implements OnInit {
  public selectedValueOdobri:string="";
  public selectedValueLozinka:string="";
  public selectedValueBlokiraj:string="";
  public korisnik:Korisnik;
  public podaci: Element1[]=[];
  public  dataSource= new MatTableDataSource(this.podaci); 
  public listaReg: Array<regKorisnik> =new Array<regKorisnik>();
  public displayedColumns: string[] = ['ime', 'prezime', 'nalog', 'korisnickoIme', 'mail'];
  public odobri:Array<string> =new Array<string>();
  public blokiraj:Array<string> =new Array<string>();
  public lozinka:Array<string> =new Array<string>();


  constructor(private router: Router, public servicePodaci : CuvanjePodatakaService,  
    private serviceAdmin:AdminService , private changeDetectorRefs: ChangeDetectorRef ) { 
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.korisnik=servicePodaci.getKorisnik();
    this.serviceAdmin.getRegKorisnike(this.korisnik.token).subscribe((result:any)=>{
      this.listaReg=result;
      console.log(this.listaReg);
      for(var i in this.listaReg){
        this.podaci.push({ime:this.listaReg[i].korisnik.ime, prezime:this.listaReg[i].korisnik.prezime,
        nalog: this.listaReg[i].stanjeNaloga.stanje, korisnickoIme: this.listaReg[i].korisnik.korisnickoIme,
        mail:this.listaReg[i].korisnik.mail});
        this.lozinka.push(this.listaReg[i].korisnik.korisnickoIme);
        if(this.listaReg[i].stanjeNaloga.id==3){
          this.odobri.push(this.listaReg[i].korisnik.korisnickoIme);
        }
        if(this.listaReg[i].stanjeNaloga.id==1){
          this.blokiraj.push(this.listaReg[i].korisnik.korisnickoIme);
        }
      }
      console.log(this.dataSource);
      this.dataSource=new MatTableDataSource(this.podaci);
      
    })

  }

  public pocetna(){
    this.router.navigateByUrl('admin');
  }

  ngOnInit(): void {
    var a=this.servicePodaci.idAdmin();
    if(this.korisnik==null || a=="false"){
      this.router.navigateByUrl("/");
    }
    (<HTMLInputElement>document.getElementById("obav")).style.display="none";
  }

  public odobriNalog(){
    this.serviceAdmin.updateOdobriNalog(this.korisnik.token, this.selectedValueOdobri).subscribe((result:any)=>{
      console.log("odobren");
      
    });
    (<HTMLInputElement>document.getElementById("tekst")).innerHTML="Uspješno ste odobrili nalog.";
    (<HTMLInputElement>document.getElementById("obav")).style.display="block";
  }

  public blokirajNalog(){
    this.serviceAdmin.updateBlokirajNalog(this.korisnik.token, this.selectedValueBlokiraj).subscribe((result:any)=>{
      console.log("blokiran");
      
    });
    (<HTMLInputElement>document.getElementById("tekst")).innerHTML="Uspješno ste blokirali nalog.";
    (<HTMLInputElement>document.getElementById("obav")).style.display="block";
  }

  public resetujLozinku(){
    this.serviceAdmin.updateResetujLozinku(this.korisnik.token, this.selectedValueLozinka).subscribe((result:any)=>{
      console.log("resetovana lozinka");  
    });
    (<HTMLInputElement>document.getElementById("tekst")).innerHTML="Uspješno ste resetovali lozinku.";
    (<HTMLInputElement>document.getElementById("obav")).style.display="block";
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
