import { Korisnik } from "./korisnik.model";
import { StanjeNaloga } from "./stanjeNaloga.model";

export class regKorisnik{
    
    public korisnik : Korisnik=new Korisnik(0,"","","","","","",0);
    public korisnikId:number=0;
    public stanjeNaloga : StanjeNaloga=new StanjeNaloga();
    

    constructor(){}
    
}