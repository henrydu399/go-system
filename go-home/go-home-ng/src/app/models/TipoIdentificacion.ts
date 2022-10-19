export class TipoIdentificacion{

	id:number;
	prefijo:string;
	tipo:string;

    constructor (_id : number , _prefijo : string , _tipo:string){
        this.id= _id;
        this.prefijo = _prefijo;
        this.tipo = _tipo;
    }

}