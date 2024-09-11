import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPocetnaComponent } from './admin-pocetna/admin-pocetna.component';
import { KorisnikPocetnaComponent } from './korisnik-pocetna/korisnik-pocetna.component';
import { KreiranjePosjetaComponent } from './kreiranje-posjeta/kreiranje-posjeta.component';
import { KupovinaComponent } from './kupovina/kupovina.component';
import { PregledLogovaComponent } from './pregled-logova/pregled-logova.component';
import { PrvaKomponentaComponent } from './prva-komponenta/prva-komponenta.component';
import { RadSaKorisnicimaComponent } from './rad-sa-korisnicima/rad-sa-korisnicima.component';
import { UnosMuzejaComponent } from './unos-muzeja/unos-muzeja.component';


const routes: Routes = [
  {
    path:'',
    component:PrvaKomponentaComponent
  },
  {
    path:'admin',
    component:AdminPocetnaComponent
  },
  {
    path:'korisnik',
    component:KorisnikPocetnaComponent
  },
  {
    path:'radSaKorisnicima',
    component:RadSaKorisnicimaComponent
  },
  {
    path:'pregledLogova',
    component:PregledLogovaComponent
  },
  {
    path:'unosMuzeja',
    component:UnosMuzejaComponent
  },
  {
    path:'kreiranjePosjeta',
    component:KreiranjePosjetaComponent
  },
  {
    path:'kupovina',
    component:KupovinaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
