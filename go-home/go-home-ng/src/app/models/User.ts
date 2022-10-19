
import { environment, Global } from "src/environments/environment.prod";
import { Persona } from "./Persona";
import { PrivilegioRolUsuario } from "./PrivilegioRolUsuario";
import { UsuarioPK } from "./UsuarioPK";

export class Usuario {

    id!: UsuarioPK| null;
    email!: string| null;
    fechaCreacion!: Date| null;
    movil!: string| null;
    username!: string| null;
    password!: string| null;
    persona!: Persona| null;

    token!: string| null;
    roles!: string| null;
    sistema:string| null;

    privilegios!:PrivilegioRolUsuario[];

    constructor(){
        this.sistema = Global.sistemaName;
    }



}