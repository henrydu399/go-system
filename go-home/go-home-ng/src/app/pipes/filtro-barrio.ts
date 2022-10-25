import { Pipe, PipeTransform } from '@angular/core';
import { Barrio } from '../models/Barrio';
import { TipoIdentificacion } from '../models/TipoIdentificacion';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroBarrio'
})
export class FiltroBarrioPipe implements PipeTransform {

  transform(aliados: Barrio[], search: string = ''): Barrio[] {

    if (search.length === 0)
      return aliados;

    const filteredAliados = aliados.filter(obj => (obj.nombre + "." ).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
