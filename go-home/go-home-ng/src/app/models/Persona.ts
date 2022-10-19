import { PersonaPK } from "./PersonaPK";
import { TipoIdentificacion } from "./TipoIdentificacion";

export class Persona{

     id!: PersonaPK;

	 apellidos!: string | null;

	 edad!: number;

	 estadoCivil!: string;

	 fecha_nacimiento!: Date;

	 fechaCreacion!: Date;

	 nombres!: string| null;

	 numeroHijos!: number;

	 profesion!: string;

	 sexo!: string;

     tipoIdentificacion!: TipoIdentificacion;

     


     

}