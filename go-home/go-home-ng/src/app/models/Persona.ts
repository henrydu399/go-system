import { PersonaContacto } from "./PersonaContacto";
import { PersonaPK } from "./PersonaPK";
import { PersonaSistema } from "./PersonaSistema";
import { TipoIdentificacion } from "./TipoIdentificacion";

export class Persona{

     id!: PersonaPK;

	 apellidos!: string | null;

	 edad!: number;

	 estadoCivil!: string;

	 fechaNacimiento!: Date;

	 fechaCreacion!: Date;

	 nombres!: string| null;

	 numeroHijos!: number;

	 profesion!: string;

	 sexo!: string;

	 nivelEscolaridad!:string;

     tipoIdentificacion!: TipoIdentificacion;


	 listPersonaContacto!: PersonaContacto[];

	 personaSistema!:PersonaSistema;

     


     

}