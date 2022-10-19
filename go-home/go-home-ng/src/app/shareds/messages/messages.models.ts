import { mensaje } from "src/app/constants/menssagesConstants";


export class Messages{
    public id : number = Math.random();
    public mostrarAlerta: boolean = false;
    public msgAlerta: string = "";
    public msgTipo: string = mensaje.INFO;
}