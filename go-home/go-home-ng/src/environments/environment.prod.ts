export const Global = {
  sistemaName:'GO-HOME',
  production: true,
  loginIsRequeride :true,
  gateway :'http://localhost:8080'
  
};


export const environment = {
  url :Global.gateway,
  production: true,
  getAllUsuarios:Global.gateway +'/home/usuario/',
  getAllDepartamentos: Global.gateway +'/home/departamento/',
  getAllCiudades: Global.gateway +'/home/ciudad/',
  getAllBarrios: Global.gateway +'/home/barrio/',
  login: Global.gateway +'/home/login/',
  register: Global.gateway +'/home/usuario/public/',

  patternEmail: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,

}