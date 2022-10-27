import { PersonaContactoPK } from "./PersonaContactoPK";


export class PersonaContacto {


    id!: PersonaContactoPK;


    movil!: string;


    tel!: string;


    direccion!: string;


    fechaCreacion!: Date;


    activo!: boolean;

    idDepartamento!: number;

    idCiudad!: number;

    idBarrio!: number;


}