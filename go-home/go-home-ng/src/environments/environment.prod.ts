export const Global = {
  sistemaName:'GO-HOME',
  production: true,
  loginIsRequeride :true,
  gateway :'http://localhost:8080'
  
};


export const environment = {
  url :Global.gateway,
  production: true,
  pathPersonas:Global.gateway +'/home/persona/',
  findPersonas:Global.gateway +'/home/persona/find/',
  findUser:Global.gateway +'/home/usuario/find/',
  pathDeletePersona:Global.gateway  +'/home/persona/delete/',
  confirmUser:Global.gateway +'/home/usuario/confirm/',
  pathRolesSistema: Global.gateway + '/home/rolesSistema/',
  pathUsuarios:Global.gateway +'/home/usuario/',
  pathSaveForSystem:Global.gateway  +'/home/usuario/saveForSystem/',
  pathDeleteUser:Global.gateway  +'/home/usuario/delete/',
  pathDesactivateUSer:Global.gateway  +'/home/usuario/desactivate/',
  pathEdithForSystem:Global.gateway +'/home/usuario/edithForSystem/',
  pathDepartamentos: Global.gateway +'/home/departamento/public/',
  pathCiudades: Global.gateway +'/home/ciudad/public/',
  pathBarrios: Global.gateway +'/home/barrio/public/',
  pathTipoDocumentos: Global.gateway +'/home/tipoDocumento/',
  login: Global.gateway +'/home/login/',
  register: Global.gateway +'/home/usuario/public/',

  patternEmail: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,

}