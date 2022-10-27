
import { environment, Global } from "src/environments/environment.prod";
import { Persona } from "./Persona";
import { PersonaContacto } from "./PersonaContacto";
import { PrivilegioRolUsuario } from "./PrivilegioRolUsuario";
import { RolesUsuario } from "./RolesUsuario";
import { RolSistema } from "./RolSistema";
import { UsuarioPK } from "./UsuarioPK";

export class Usuario {

    id!: UsuarioPK| null;
    email!: string| null;
    fechaCreacion!: Date| null;
    movil!: string| null;
    username!: string| null;
    password!: string| null;
    persona!: Persona| null;

    activo!:boolean;
	
	confirmado!:boolean;

    token!: string| null;
    tokenActivate!:string;

    roles!: string| null;
    sistema:string| null;

    rol! :RolSistema;

    personaContacto! :PersonaContacto;

    privilegios!:PrivilegioRolUsuario[];

    constructor(){
        this.sistema = Global.sistemaName;
    }



}