import { Privilegio } from "./Privilegio";
import { PrivilegioRolUsuarioPK } from "./PrivilegioRolUsuarioPK";

export class PrivilegioRolUsuario{

    id!:PrivilegioRolUsuarioPK;
    fechaCreacion!:Date;
    crear!:boolean;
    editar!:boolean;
    buscar!:boolean;
    getAll!:boolean;
    getNormal!:boolean;
    desactivar!:boolean;
    eliminar!:boolean;
    privilegio!:Privilegio;

    //TEMPORAL PARA MENUS
    listPrivilegios!:PrivilegioRolUsuario[] ;

}