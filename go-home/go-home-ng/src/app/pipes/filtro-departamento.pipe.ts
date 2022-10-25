import { Pipe, PipeTransform } from '@angular/core';
import { Departamento } from '../models/Departamento';
import { TipoIdentificacion } from '../models/TipoIdentificacion';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroDepartamento'
})
export class FiltroDepartamentoipe implements PipeTransform {

  transform(aliados: Departamento[], search: string = ''): Departamento[] {

    if (search.length === 0)
      return aliados;

    const filteredAliados = aliados.filter(obj => (obj.nombre ).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
