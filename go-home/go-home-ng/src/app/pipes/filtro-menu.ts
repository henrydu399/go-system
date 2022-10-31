import { Pipe, PipeTransform } from '@angular/core';
import { Privilegio } from '../models/Privilegio';
import { PrivilegioRolUsuario } from '../models/PrivilegioRolUsuario';
import { TipoIdentificacion } from '../models/TipoIdentificacion';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroMenu'
})
export class FiltroMenuPipe implements PipeTransform {

  transform(aliados: PrivilegioRolUsuario[]): PrivilegioRolUsuario[] {
  
    const filteredAliados = aliados.filter( (obj) => obj.privilegio.visibleMenu );


    return filteredAliados;
  }

}
