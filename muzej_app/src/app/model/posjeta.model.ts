import { Time } from "@angular/common";
import { Prezentacija } from "./prezentacija.model";
import { Vrijeme } from "./Vrijeme.model";

export class Posjeta{
    public id : number | undefined;
    public datum : Date | undefined;
    public vrijeme :Time | undefined;
    public trajanje : number | undefined;
    public cijena : number | undefined;
    public prezentacijaByPrezentacijaId: Prezentacija =new Prezentacija();
}