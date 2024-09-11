# Internet programiranje 

### Zadatak: Web aplikaciju za virtuelne posjete svjetskim muzejima  
  
Implementirati Web aplikaciju za virtuelne posjete svjetskim muzejima. Muzej karakteriše naziv, adresa,
broj telefona, grad, država, geolokacija prikazana na Google mapi i tip muzeja. Podaci o muzejima se
čuvaju u bazi podataka i potrebno je unijeti bar 50 stvarnih zapisa u inicijalnu bazu. Sve korisničke akcije
je potrebno logovati. Aplikacija nudi kao RESTful servis spisak svih muzeja i spisak svih virtuelnih posjeta
u JSON formatu. Potrebno je implementirati autorizaciju pristupa ovim servisima na proizvoljno odabran
način i uz samu implementaciju aplikacije priložiti i kratak izvještaj o testiranju izloženih funkcionalnosti
korištenjem Postman alata.  
Aplikacija mora da razlikuje tri grupe korisnika: gost (vidi samo početnu stranicu aplikacije i formu za
registraciju), registrovani korisnik i administrator.  
Registrovani korisnik može da vidi listu svih dostupnih muzeja, da ih pretražuje po nazivu i gradu, kao i
da pregleda sve aktivne virtuelne posjete. Registrovani korisnici treba da otvore korisnički nalog.
Otvaranje naloga se obavlja na odgovarajućoj stranici gdje se unose ime, prezime, korisničko ime, lozinka
(2 puta zbog provjere) i e-mail adresa. Registrovani korisnik nakon pretrage može da otvori stranicu sa
detaljima o muzeju i na njoj može da kupi ulaznicu za virtuelnu posjetu muzeju ukoliko ima otvoren
račun u VirtualBank banci, opisanoj u nastavku. Nakon uspješne kupovine karte, karta se šalje u PDF
formatu na e-mail korisnika. Karta sadrži sve podatke o virtuelnoj turi i slučajno generisani broj karte.
Registrovanim korisnicima se prikazuju i vijesti iz kulture konzumiranjem RSS feed-a dostupnog na
https://www.huffpost.com/section/arts/feed. Na stranici za prikaz detalja o određenom muzeju,
prikazuje se i vremenska prognoza za tri slučajno odabrana grada iz države u kojoj se muzej nalazi.
Prognozu preuzimati sa https://openweathermap.org/api.  
Administratori koriste administratorski dio aplikacije. Administratori su jedini korisnici ove aplikacije i
moraju biti prijavljeni na sistem da bi mogli da ga koriste. Nalozi se administriraju kroz JSP aplikaiciju
(CRUD). Obavezno je da bude JSP, ne JSP M2. Iz administratorskog dijela klikom na link pristupa se JSP
aplikaciji, a kroz URL se šalje token kojim se administrator autorizuje na tu drugu aplikaciju. Tokeni se
čuvaju u bazi podataka. Aplikacija na početnoj stranici prikazuje broj trenutno aktivnih korisnika glavne
aplikacije, ukupan broj registrovanih korisnika i grafikon na kojem je prikazan broj korisnika po satima u
prethodnih 24h. Osim toga, administrator može odobriti registraciju korisnika, blokirati bilo kojeg
korisnika i resetovati njegovu lozinku na neku slučajno odabranu vrijednost. Sistem koristi istu bazu
podataka kao i glavna aplikacija. Grafikon se može implementirati pomoću Chart.JS biblioteke.
Administratori su zaduženi i za unos novih muzeja kroz aplikaciju. Prilikom unosa muzeja, potrebno je
izabrati država u kojoj se muzej nalazi iz liste ponuđenih država. Korisnik može da bude samo iz neke od
evropskih država koje se popunjavaju konzumiranjem RESTful servisa dostupnog na
https://restcountries.com/v3.1/region/europe. Korištenjem drugog RESTful servisa, a na osnovu
vrijednosti alpha2Code za državu, automatski se popunjavaju regioni te države, a na osnovu regiona i
gradovi konzumiranjem odgovarajućih servisa sa http://battuta.medunes.net/. Administratori aplikacije
kreiraju i termine virtuelnih posjeta muzeju. Svaki termin posjeta ima datum i vrijeme početka i trajanje
u satima. Virtuelna prezentacija muzeja se kreira tako što se upload-uje 5-10 slika i jedan video (YouTube
link ili .mp4 video fajl) i ovi upload-ovani materijali su vidljivi korisiniku kada započne virtuelnu posjetu.
Virtuelne posjete imaju ograničeno vrijeme trajanja (određen broj sati u jednom danu) i nakon toga slike
i video se postavljaju na nevidljivo. Korisnika je potrebno obavijestiti sat vremena prije početka termina
virtuelne posjete za koju je kupio ulaznicu, kao i 5 minuta prije kraja same posjete. Kada se završi
posjeta, korisniku je potrebno ugasiti sesiju. Administratori mogu pregledati i logove, kao i preuzeti ih u
PDF formatu.  
VirtualBank je pseudo banka implementiran u svrhu ilustracije kartičnog plaćanja za potrebe aplikacije
za virtuelne posjete muzejima. Funkcionalnost plaćanja je potrebno implementirati kao RESTful servis.
Podaci koje korisnik unosi na formu na stranici za plaćanje su: ime i prezime klijenta, broj kreditne
kartice, tip kreditne kartice (VISA, MASTERCARD, AMERICAN EXPRESS), datum isticanja kreditne kartice
(mjesec i godina) i pin. Validaciju unesenih podataka obavlja Web servis banke. Ukoliko su uneseni podaci
ispravni i ako korisnik na računu ima dovoljno sredstava uplata će biti prihvaćena. Servis banke na
odgovarajući način signalizira aplikaciji da je transakcija uspješna kako bi aplikacija za virtuelne posjete
muzeju mogla poslati kupljenu kartu u elektronskoj formi za odgovarajuću posjetu i kako bi pamtila ovu
informaciju kod sebe i koristila je kao potvrdu da korisnik može da pristupi samoj posjeti. Osim
navedenog, VirtualBank ima i jednostavnu JSP M2 aplikaciju, koja korisnicima banke omogućava pregled
transakcija i omogućavanje/ukidanje mogućnosti plaćanja (kao neka vrsta sigurnosnog mehanizma).
