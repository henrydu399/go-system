import { Pipe, PipeTransform } from '@angular/core';
import { Persona } from '../models/Persona';
import { RolesUsuario } from '../models/RolesUsuario';
import { Usuario } from '../models/User';

@Pipe({
  name: 'filtroRolUsuario'
})
export class FiltroRolUsuarioPipe implements PipeTransform {

  transform(personas: RolesUsuario[], search: string = ''): RolesUsuario[] {

    if (search.length === 0)
      return personas;

    const filteredAliados = personas.filter(obj => ( obj?.id.nombreRol + "."  + obj?.usuario.persona + "." + obj?.usuario.persona?.apellidos+ "." + obj?.id.numeroIdentificacion).toLocaleLowerCase().includes(search.toLocaleLowerCase()));

    return filteredAliados;
  }

}
