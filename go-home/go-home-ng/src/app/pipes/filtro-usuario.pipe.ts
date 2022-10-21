import { Pipe, PipeTransform } from '@angular/core';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroUsuario'
})
export class FiltroUsuarioPipe implements PipeTransform {

  transform(aliados: Usuario[], search: string = ''): Usuario[] {

    if (search.length === 0)
      return aliados;

    const filteredAliados = aliados.filter(obj => (obj.email + "." + obj.persona?.nombres + "." + obj.persona?.apellidos+ "." + obj.persona?.id.numeroIdentificacion).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
