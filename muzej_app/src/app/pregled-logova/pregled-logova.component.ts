import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Korisnik } from '../model/korisnik.model';
import { Log } from '../model/log.model';
import { AdminService } from '../servisi/admin.service';
import { CuvanjePodatakaService } from '../servisi/cuvanje-podataka.service';
import jspdf from 'jspdf';  
import html2canvas from 'html2canvas';  

export interface Element1 {
  id: number;
  ime:string;
  prezime:string;
  korisnickoIme: string;
  aktivnost: string;
  vrijeme:Date;  
}

declare var require: any
const FileSaver = require('file-saver');

@Component({
  selector: 'app-pregled-logova',
  templateUrl: './pregled-logova.component.html',
  styleUrls: ['./pregled-logova.component.css']
})


export class PregledLogovaComponent implements OnInit {
  public podaci:Element1[]=[];
  public  dataSource= new MatTableDataSource(this.podaci); 
  public niz: Array<Log>=new Array<Log>();
  public korisnik:Korisnik;
  public displayedColumns: string[] = ['id','ime', 'prezime', 'korisnickoIme', 'aktivnost', 'vrijeme'];
  
  constructor(private router:Router, private adminServis : AdminService,
    private servicePodaci : CuvanjePodatakaService) { 
    this.korisnik=new Korisnik(0,"","","","","","",0);
    this.korisnik=servicePodaci.getKorisnik();
    this.adminServis.getLogove(this.korisnik.token).subscribe((result : any)=>{
      this.niz=result;
      
      for(var i in this.niz){
        this.podaci.push({id: this.niz[i].id, korisnickoIme:this.niz[i].korisnikByKorisnikId.korisnickoIme,
        ime:this.niz[i].korisnikByKorisnikId.ime, prezime:this.niz[i].korisnikByKorisnikId.prezime, aktivnost: this.niz[i].aktivnost,
        vrijeme: this.niz[i].vrijeme});
      }
      this.dataSource=new MatTableDataSource(this.podaci);
     
      
    })
    
  }

  ngOnInit(): void {
    var a=this.servicePodaci.idAdmin();
    if(this.korisnik==null || a=="false"){
      this.router.navigateByUrl("/");
    }
  }


  public captureScreen()  
  {  
    var data = document.getElementById('tabela');  
    if(data!=null){
      html2canvas(data).then(canvas => {  
        // Few necessary setting options  
        var imgWidth = 208;   
        var pageHeight = 295;    
        var imgHeight = canvas.height * imgWidth / canvas.width;  
        var heightLeft = imgHeight;  

        const contentDataURL = canvas.toDataURL('image/png')  
        let pdf = new jspdf('p', 'mm', 'a4'); // A4 size page of PDF  
        var position = 10;  
        pdf.addImage(contentDataURL, 'PNG', 1, position, imgWidth, imgHeight)  
        pdf.save('logger.pdf'); // Generated PDF   
      });  
    }
  }  

  public pocetna(){
    this.router.navigateByUrl('admin');
  }

  public preuzmi(){
    this.captureScreen();
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
