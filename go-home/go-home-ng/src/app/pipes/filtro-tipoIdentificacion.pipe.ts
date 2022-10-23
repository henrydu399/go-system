import { Pipe, PipeTransform } from '@angular/core';
import { TipoIdentificacion } from '../models/TipoIdentificacion';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroTipoIdentificacion'
})
export class FiltroTipoIdentificacionPipe implements PipeTransform {

  transform(aliados: TipoIdentificacion[], search: string = ''): TipoIdentificacion[] {

    if (search.length === 0)
      return aliados;

    const filteredAliados = aliados.filter(obj => (obj.nombre + "." + obj.prefijo).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
