export class Privilegio{
    id!:number;
	nombre!:string;
	descripcion!:string;
	level!:number;
	activo!:boolean;
	fkIdPrivilegio!:number;
	url!:string;
	isVisibleMenu!:boolean;
	iconoClass!:string;
	idSistema!:number;
	fechaCreacion!:Date;
}