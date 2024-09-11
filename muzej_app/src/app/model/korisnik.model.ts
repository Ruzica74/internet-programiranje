export class Korisnik{
    
    public id: number;   
    public ime: string;    
    public prezime: string;    
    public korisnickoIme: string;    
    public lozinka: string;    
    public mail: string;   
    public token: string;
    public aktivan: number;
    

    constructor(id:number,ime:string, prezime:string, kIme:string, lozinka:string,
        mail:string, token:string, aktivan:number ){
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
        this.aktivan=aktivan;
        this.lozinka=lozinka;
        this.mail=mail;
        this.token=token;
        this.korisnickoIme=kIme;
    }

    
}