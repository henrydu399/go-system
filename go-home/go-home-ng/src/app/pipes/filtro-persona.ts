import { Pipe, PipeTransform } from '@angular/core';
import { Persona } from '../models/Persona';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroPersona'
})
export class FiltroPersonaPipe implements PipeTransform {

  transform(personas: Persona[], search: string = ''): Persona[] {

    if (search.length === 0)
      return personas;

    const filteredAliados = personas.filter(obj => ( obj?.nombres + "." + obj?.apellidos+ "." + obj?.id.numeroIdentificacion).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
