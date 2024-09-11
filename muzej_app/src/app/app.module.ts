import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgChartsModule } from 'ng2-charts';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PrvaKomponentaComponent } from './prva-komponenta/prva-komponenta.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import { AdminPocetnaComponent } from './admin-pocetna/admin-pocetna.component';
import { KorisnikPocetnaComponent } from './korisnik-pocetna/korisnik-pocetna.component'
import { DatePipe } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import { RadSaKorisnicimaComponent } from './rad-sa-korisnicima/rad-sa-korisnicima.component';
import { PregledLogovaComponent } from './pregled-logova/pregled-logova.component';
import { UnosMuzejaComponent } from './unos-muzeja/unos-muzeja.component';
import { KreiranjePosjetaComponent } from './kreiranje-posjeta/kreiranje-posjeta.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {NgxMatTimepickerModule} from 'ngx-mat-timepicker';
import { KupovinaComponent } from './kupovina/kupovina.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import { GoogleMapsModule } from '@angular/google-maps';



@NgModule({
  declarations: [
    AppComponent,
    PrvaKomponentaComponent,
    AdminPocetnaComponent,
    KorisnikPocetnaComponent,
    RadSaKorisnicimaComponent,
    PregledLogovaComponent,
    UnosMuzejaComponent,
    KreiranjePosjetaComponent,
    KupovinaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule, 
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    NgChartsModule,
    MatTableModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    NgxMatTimepickerModule,
    MatDividerModule,
    MatListModule,
    GoogleMapsModule,
    
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
