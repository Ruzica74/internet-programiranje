import { Lokacija } from "./lokacija.model";
import { TipMuzeja } from "./tipMuzeja.model";

export class Muzej{
    public id:number=0;
    public naziv:string="";
    public tipMuzeja: TipMuzeja=new TipMuzeja();
    public brojTelefona: string='';
    public lokacija :Lokacija=new Lokacija();

    constructor(){}
}