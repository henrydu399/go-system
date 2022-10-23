export class TipoIdentificacion{

	id:number;
	nombre:string;
	prefijo:string;

    constructor (_id : number , _nombre : string , _prefijo:string){
        this.id= _id;
        this.nombre = _nombre;
        this.prefijo = _prefijo;
    }

}