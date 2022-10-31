export const Global = {
  sistemaName:'GO-HOME',
  production: true,
  loginIsRequeride :true,
  gateway :'http://localhost:8080'
  
};


export const environment = {
  url :Global.gateway,
  production: true,
  findUser:Global.gateway +'/home/usuario/find/',
  confirmUser:Global.gateway +'/home/usuario/confirm/',
  pathRolesSistema: Global.gateway + '/home/rolesSistema/',
  pathUsuarios:Global.gateway +'/home/usuario/',
  pathSaveForSystem:Global.gateway  +'/home/usuario/saveForSystem/',
  pathDeleteUser:Global.gateway  +'/home/usuario/delete/',
  pathDesactivateUSer:Global.gateway  +'/home/usuario/desactivate/',
  pathEdithForSystem:Global.gateway +'/home/usuario/edithForSystem/',
  pathDepartamentos: Global.gateway +'/home/departamento/',
  pathCiudades: Global.gateway +'/home/ciudad/',
  pathBarrios: Global.gateway +'/home/barrio/',
  pathTipoDocumentos: Global.gateway +'/home/tipoDocumento/',
  login: Global.gateway +'/home/login/',
  register: Global.gateway +'/home/usuario/public/',

  patternEmail: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,

}