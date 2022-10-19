export class UsuarioPK{

     id: number;
	 idTipoIdentificacion : number;
	 numeroIdentificacion: string;

    constructor(_id: number , _idTipoIdentificacion : number , _numeroIdentificacion : string){
        this.id = _id;
        this.idTipoIdentificacion = _idTipoIdentificacion;
        this.numeroIdentificacion = _numeroIdentificacion;
    }

/*     getId():number{
        return this.id;
    }

    getIdTipoIdentificacion():number{
        return this.idTipoIdentificacion;
    }

    getNumeroIdentificacion():string{
        return this.numeroIdentificacion;
    }

    setId(_id : number):void{
        this.id = _id;
    }

    setIdTipoIdentificacion(_idTipoIdentificacion : number):void{
        this.idTipoIdentificacion = _idTipoIdentificacion;
    }

    setNumeroIdentificacion(_numeroIdentificacion : string):void{
        this.numeroIdentificacion = _numeroIdentificacion;
    } */


}