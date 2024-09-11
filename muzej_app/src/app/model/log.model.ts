import { Korisnik } from "./korisnik.model";

export class Log{
    public aktivnost:string="";
    public vrijeme:Date=new Date();
    public korisnikByKorisnikId : Korisnik=new Korisnik(0,"","","","","","",0);
    public id: number=0;
}