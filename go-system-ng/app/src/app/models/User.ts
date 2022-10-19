import { environment } from "src/environments/environment.prod";
import { Persona } from "./Persona";
import { UsuarioPK } from "./UsuarioPK";

export class Usuario {

    id!: UsuarioPK;
    email!: string;
    fechaCreacion!: Date;
    movil!: string;
    username!: string;
    password!: string;
    persona!: Persona;

    token!: string;
    roles!: string;
    sistema:string;

    constructor(){
        this.sistema = environment.sistemaName;
    }



}