import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from '../model/korisnik.model';
import { Muzej } from '../model/muzej.model';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';

@Component({
  selector: 'app-kupovina',
  templateUrl: './kupovina.component.html',
  styleUrls: ['./kupovina.component.css']
})
export class KupovinaComponent implements OnInit {
  public korisnik:Korisnik;
  public muzej:Muzej;
  constructor(private router:Router, private servicePodaci :CuvanjePodatakaService) { 
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.korisnik=servicePodaci.getKorisnik();
    this.muzej=new Muzej();
    this.muzej=servicePodaci.getMuzej();
  }

  ngOnInit(): void {
    if(this.korisnik==null){
      this.router.navigateByUrl("/");
    }
  }

}
