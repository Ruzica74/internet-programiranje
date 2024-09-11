import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Korisnik } from '../model/korisnik.model';
import { Muzej } from '../model/muzej.model';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';
import { KorisnikService } from '../servisi/korisnik.service';

export interface Tabela {
  name: string;
  tip: string;
  drzava: string;
  grad: string;
  id: number;
}

export interface AktivnePosjete{
  nazivMuzeja:string;
  datum:Date;
  vrijeme:Time;
  trajanje:number;
  cijena:number;
  id:number;
}

@Component({
  selector: 'app-korisnik-pocetna',
  templateUrl: './korisnik-pocetna.component.html',
  styleUrls: ['./korisnik-pocetna.component.css']
})


export class KorisnikPocetnaComponent implements OnInit {
  public korisnik:Korisnik;
  public podaci:Tabela[]=[];
  public muzeji:Muzej[]=[];
  public odabraniMuzej:Muzej | undefined;
  public podaciPosjeta:AktivnePosjete[]=[];
  public  dataSource= new MatTableDataSource(this.podaci);
  public  dataSource1= new MatTableDataSource(this.podaciPosjeta); 
  public displayedColumns: string[] = ['name', 'tip', 'drzava', 'grad', 'id'];
  constructor(private router:Router, private servicePodaci :CuvanjePodatakaService, 
    private korisnikService: KorisnikService) { 
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.korisnik=servicePodaci.getKorisnik();
    this.korisnikService.getMuzeji(this.korisnik.token).subscribe((result: any)=>
    {
      console.log(result);
      this.muzeji=result;
      for(var i in result){
        this.podaci.push({name:result[i].naziv,tip:result[i].tipMuzeja.tip, drzava:result[i].lokacija.drzava, 
          grad:result[i].lokacija.grad, id:result[i].id});
      }
      this.dataSource=new MatTableDataSource(this.podaci);

    })
  }

  ngOnInit(): void {
    if(this.korisnik==null){
      this.router.navigateByUrl("/");
    }
  }

  klik(id:number){
    for(var i in this.muzeji){
      if(this.muzeji[i].id==id){
        this.odabraniMuzej=this.muzeji[i];
      }
    }
    localStorage.setItem('muzej',JSON.stringify(this.odabraniMuzej) );
    this.odabraniMuzej=undefined;
    this.router.navigateByUrl('kupovina');
  }

}
