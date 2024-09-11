import { Muzej } from "./muzej.model";

export class Prezentacija{
    public id:number |undefined;
    public video: string | undefined;
    public muzejByMuzejId : Muzej | undefined;
}