import { RolesUsuarioPK } from "./RolesUsuarioPK";
import { RolSistema } from "./RolSistema";
import { Usuario } from "./User";

export class RolesUsuario{
    id!:RolesUsuarioPK;
    rolesSistema!:RolSistema;
    usuario!:Usuario;
}