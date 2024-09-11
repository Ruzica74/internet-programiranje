
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';

import { BaseChartDirective } from 'ng2-charts';
import { Korisnik } from '../model/korisnik.model';
import { AdminService } from '../servisi/admin.service';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';
import { PrijavaService } from '../servisi/prijava.service';

@Component({
  selector: 'app-admin-pocetna',
  templateUrl: './admin-pocetna.component.html',
  styleUrls: ['./admin-pocetna.component.css']
})
export class AdminPocetnaComponent implements OnInit {
  //@ViewChild(BaseChartDirective) chart: BaseChartDirective;
  public brojRegistrovanih:number=0;
  public lista: Array<number>=new Array();
  public brojAktivnih:number=0;
  public barChartLabels:string[] = [];
  public barChartLegend = true;
  public barChartData = [
    {data: this.lista, label: 'Broj aktivnih korisnika po satima', backgroundColor: [
      '#c2185b'
    ],},
    
  ];
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
    
  };
  public korisnik:Korisnik;
  date: Date=new Date();
  


  
  constructor(private router :Router,  private service :  PrijavaService,
    public servicePodaci : CuvanjePodatakaService, private serviceAdmin:AdminService) { 
   
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.korisnik=servicePodaci.getKorisnik();
    console.log(servicePodaci.korisnik);
    this.serviceAdmin.getBrojRegistrovanih(this.korisnik.token).subscribe((result:any)=>{
      this.brojRegistrovanih=result;
      console.log(result);
    })
    this.serviceAdmin.getBrojTrenutnoAktivnih(this.korisnik.token).subscribe((result:any)=>{
      this.brojAktivnih=result;
    })
    
    this.serviceAdmin.getBrojAktivnihPoSatima(this.korisnik.token).subscribe((result: any)=>{
      this.lista=result;
      this.barChartData[0].data=this.lista;
      this.date=new Date();
      for(let i=0 ;i<24;i++){
        var jedan=this.date.getHours()-i;
        var dva=(this.date.getHours()-i-1);
        if(jedan<0){
          jedan=24+jedan;
        }
        if(dva<0){
          dva=24+dva;
        }
        this.barChartLabels.push(dva+"-"+jedan);
      }
    })  
  }

  ngOnInit(): void {
    var a=this.servicePodaci.idAdmin();
    if(this.korisnik==null || a=="false"){
      this.router.navigateByUrl("/");
    }
  }

  uzmiKorisnika(){
    var broj=0;
    var interval = setInterval(() => {
      if(broj > 2) {
        broj+=1;
      } else {
        this.service.getKorisnik(this.korisnik.korisnickoIme).subscribe((result:any)=>{
          console.log(result.token);
          this.korisnik=result;
        })
        clearInterval(interval);
      }
    },1000)

    
  }
  

  public odlogujSe(){
    localStorage.setItem("korisnik", "");
    this.router.navigateByUrl("/");
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
