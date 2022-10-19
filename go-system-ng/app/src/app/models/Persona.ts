import { PersonaPK } from "./PersonaPK";
import { TipoIdentificacion } from "./TipoIdentificacion";

export class Persona{

     id!: PersonaPK;

	 apellidos!: string;

	 edad!: number;

	 estadoCivil!: string;

	 fecha_nacimiento!: Date;

	 fechaCreacion!: Date;

	 nombres!: string;

	 numeroHijos!: number;

	 profesion!: string;

	 sexo!: string;

     tipoIdentificacion!: TipoIdentificacion;

     


     

}