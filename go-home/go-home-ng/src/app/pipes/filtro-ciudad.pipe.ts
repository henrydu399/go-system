import { Pipe, PipeTransform } from '@angular/core';
import { Ciudad } from '../models/Ciudad';
import { TipoIdentificacion } from '../models/TipoIdentificacion';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroCiudad'
})
export class FiltroCiudadPipe implements PipeTransform {

  transform(aliados: Ciudad[], search: string = ''): Ciudad[] {

    if (search.length === 0)
      return aliados;

    const filteredAliados = aliados.filter(obj => (obj.nombre + "." ).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
