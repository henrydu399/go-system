export class UsuarioPK{

    public id: number | null;
    public idTipoIdentificacion : number;
    public numeroIdentificacion: string;

    constructor(public _id: number | null ,public  _idTipoIdentificacion : number ,public  _numeroIdentificacion : string){
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