package administradorUsers.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gosystem.commons.adminUsers.dto.PersonaContactoDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.TokenGenerator;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.components.UserComponent;
import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaContacto;
import administradorUsers.entitys.PersonaContactoPK;
import administradorUsers.entitys.PersonaPK;
import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.RolesUsuarioPK;
import administradorUsers.entitys.Systema;
import administradorUsers.entitys.Usuario;
import administradorUsers.entitys.UsuarioPK;
import administradorUsers.enums.MethodsAdminUSerEnum;
import administradorUsers.mappers.PersonaContactoMapper;
import administradorUsers.mappers.PersonaContactoPKMapper;
import administradorUsers.mappers.RolesUsuarioMapper;
import administradorUsers.mappers.UsuarioMapper;
import administradorUsers.mappers.UsuarioPKMapper;
import administradorUsers.repository.IPersonaContactoRepository;
import administradorUsers.repository.IPersonaRepository;
import administradorUsers.repository.IPrivilegiosRolUsuarioRepository;
import administradorUsers.repository.IUsuariosRepository;
import administradorUsers.repository.RolesUsuarioRepository;
import administradorUsers.services.SistemaService;
import administradorUsers.services.UserService;
import administradorUsers.utils.BCryptPasswordEncoder;
import administradorUsers.validations.UserValidation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private IUsuariosRepository repository;

	@Autowired
	private IPersonaRepository personaRepository;
	
	@Autowired
	private IPersonaContactoRepository personaContactoRepository;

	@Autowired
	private IPrivilegiosRolUsuarioRepository privilegiosRolUsuarioRepository;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private RolesUsuarioRepository rolesUsuarioRepository;

	@Autowired
	private UserValidation userValidation;

	@Autowired
	private SistemaService sistemaService;

	private Logger logger;

	BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl() {
		this.logger = UtilsLogs.getLogger(UserServiceImpl.class.getName());
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	// =======================================================================
	// ----- CRUD NORMAL -----
	// =======================================================================

	@Override
	public List<Usuario> getAll() throws AdministradorUserException {
		return repository.findAll();

	}

	@Override
	public void save(Usuario usuario) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO, usuario));
		try {

			userValidation.save(usuario);

			// #1.BUSCAMOS SI EXISTE LA PERSONA PARA GUARDAR EL USUARIO
			Optional<Persona> p = personaRepository.findById(new PersonaPK(usuario.getId().getNumeroIdentificacion(),
					usuario.getId().getIdTipoIdentificacion()));
			if (!p.isPresent()) {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO,
						ErrorConstantes.PERSONA_NO_EXISTE_EN_EL_SISTEMA);
			}

			// #2.VALIDAMOS EL SISTEMA QUE EXISTA
			Optional<Systema> sistemaFindByName = sistemaService.findBynombre(usuario.getSistema());
			if (!sistemaFindByName.isPresent()) {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO,
						ErrorConstantes.ERROR_SISTEMA_NO_EXISTE_EN_EL_SISTEMA);
			}

			// 3.VALIDAMOS QUE NO EXISTA UN USUARIO CON EL EMAIL EN EL SISTEMA
			Optional<Usuario> userFindbyEmail = this.repository.findByEmail(usuario.getEmail());
			if (userFindbyEmail.isPresent()) {

				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO,
						ErrorConstantes.USUARIO_EMAIL_YA_EXISTE);
			}

			// PASSWORD ENCRIP
			String passwordEncrip = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(passwordEncrip);
			///
			this.repository.save(usuario);
			this.repository.flush();
			
			
			this.personaContactoRepository.flush();
			
			

		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO,
					ErrorConstantes.ERROR_INTENTAR_GUARDAR);

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_GENERAL);
		}

	}

	@Override
	public void edith(Usuario usuario) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.USUARIO, usuario));

		try {
			Optional<Usuario> usuarioFind = this.repository.findById(usuario.getId());
			if (usuarioFind.isPresent()) {
				this.repository.save(usuario);
			} else {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.EDITH, LayerEnum.DAO,
						ErrorConstantes.USUARIO_YA_EXISTE);
			}

		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO,
					ErrorConstantes.ERROR_INTENTAR_MODIFICAR);

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_GENERAL);
		}

	}

	@Override
	public List<Usuario> findAll(Usuario usuario) throws AdministradorUserException {

		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.USUARIO, usuario));
		try {
			Example<Usuario> usuarioByFind = Example.of(usuario);
			List<Usuario> list = this.repository.findAll(usuarioByFind);

			if (list != null && list.size() > 0) {
				return list;
			} else {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO,
						ErrorConstantes.NO_EXISTEN_REGISTROS);
			}

		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO,
					ErrorConstantes.ERROR_CONSULTANDO);

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_GENERAL);
		}
	}

	@Override
	public Usuario find(Usuario obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.USUARIO, obj));

		List<Usuario> list = new ArrayList<>();
		try {

			if(Objects.nonNull(obj.getId() )) {// BUSCAMOS POR LA PK SI VIENE 
				if(Objects.nonNull(obj.getId().getId()) && 
						Objects.nonNull(obj.getId().getIdTipoIdentificacion()) &&
						Objects.nonNull(obj.getId().getNumeroIdentificacion()) ) {
					Optional<Usuario> u = this.repository.findById( obj.getId());
					
					
					RolesUsuarioPK rolesUsuarioPk = RolesUsuarioPK.builder()
							.idTipoIdentificacion(  obj.getId().getIdTipoIdentificacion()  )
							.numeroIdentificacion(  obj.getId().getNumeroIdentificacion()   )
							.idUsuario(  obj.getId().getId()  )
							.build();
							
							
					
					List<RolesUsuario> listRolesUsuario =  this.rolesUsuarioRepository.findByUsuario(obj);
					if( Objects.nonNull(listRolesUsuario) ) {
						listRolesUsuario.get(0).setUsuario(null);
						u.get().setRoles( UtilGson.SerializeObjet( RolesUsuarioMapper.mapper(listRolesUsuario.get(0))  ) );
					}
					
					
					if( u.isPresent()) {
						return u.get();
					}
				}
				
			}else {
				
				if (Objects.nonNull(obj.getSistema())) {
					Optional<Usuario> u = this.repository.findUserForLogin(obj.getEmail(), obj.getSistema());
					if (u.isPresent()) {
						list.add(u.get());
					}
				} else {
					Usuario usuario = (Usuario) obj;
					Example<Usuario> entityFind = Example.of(usuario);
					list = this.repository.findAll(entityFind);
				}
				
			}


			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC, null);
		}
		return null;
	}

	@Override
	public void delete(Usuario p) throws AdministradorUserException {
		// TODO Auto-generated method stub

	}

	// =======================================================================
	// -----FIN CRUD NORMAL -----
	// =======================================================================

	// =======================================================================
	// ----- CUSTOM LOGIC -----
	// =======================================================================
	@Override
	public Optional<Usuario> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	/**
	 * Metodo que busa el usuario por email y sistema
	 * 
	 * @Params Usuario Usuario.email Usuario.systema
	 * @Return Usuario (objeto y childObjets)
	 */
	public UsuarioDTO getUserForLogin(Usuario usuario) throws AdministradorUserException {
		UsuarioDTO out = null;
		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.GET_USER_FOR_LOGIN, EntityEnum.USUARIO, usuario));
		try {

			if (usuario.getEmail() == null && usuario.getSistema() == null) {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.GET_USER_FOR_LOGIN,
						LayerEnum.SERVICE, ErrorConstantes.ERROR_PARAMETROS_BUSQUEDA);
			}
			Optional<Usuario> u = this.repository.findUserForLogin(usuario.getEmail(), usuario.getSistema());

			if (u.isPresent()) {

				u.get().setPrivilegios(this.privilegiosRolUsuarioRepository.findByUsuario(u.get()));

				out = UsuarioMapper.Mapper(u.get(), true);
				return out;
			} else {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.GET_USER_FOR_LOGIN,
						LayerEnum.SERVICE, ErrorConstantes.NO_SE_ENCONTRO_USUARIO_REGISTRADO_EN_EL_SYSTEMA);
			}

		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.GET_USER_FOR_LOGIN,
					LayerEnum.SERVICE, ErrorConstantes.ERROR_CONSULTANDO_EL_REGISTROS);

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.GET_USER_FOR_LOGIN,
					LayerEnum.SERVICE, null);
		}

	}

	/**
	 * Metodo que guarda el usuario + persona + persona contacto + rol
	 * @Params Usuario
	 * @Return void O AdministradorUserException
	 */
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public UsuarioDTO saveUserSystem(UsuarioDTO usuario) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, usuario));
		try {
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  PERSONA ..."));
			Usuario u = userComponent.BuildUSerForSave(usuario);
			personaRepository.saveAndFlush(u.getPersona());
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDADO EXITOSO !"));
			
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  USUARIO ..."));
			String token = TokenGenerator.generateToken(u.getEmail());
			u.setTokenActivate(token);
			repository.saveAndFlush(u);			
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  EXITOSO !"));
			
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  ROLES USUARIO ..."));
			RolesUsuarioPK rolPk = RolesUsuarioPK.builder().
					 idSistema(usuario.getRol().getId().getIdSistema())
					.idRolSistema(usuario.getRol().getId().getId()).nombreRol(usuario.getRol().getId().getNombreRol())
					.idTipoIdentificacion(u.getId().getIdTipoIdentificacion())
					.numeroIdentificacion(u.getId().getNumeroIdentificacion()).idUsuario(u.getId().getId()).build();

			RolesUsuario rol = RolesUsuario.builder().idPk(rolPk).build();
			rolesUsuarioRepository.saveAndFlush(rol);
			logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  EXITOSO !"));
			
			
			if( Objects.nonNull(usuario.getPersona().getListPersonaContacto())  && usuario.getPersona().getListPersonaContacto().size() > 0 ) {
				logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO DATOS CONTACTO !"));
				PersonaContacto personaContacto = PersonaContactoMapper.mapper(usuario.getPersona().getListPersonaContacto().get(0)) ;
				personaContactoRepository.save(personaContacto);
				logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.SAVE_USER_SYSTEM, EntityEnum.USUARIO, null, "GUARDANDO  EXITOSO !"));
			}
			UsuarioDTO userU = UsuarioMapper.Mapper(u, false);
			return userU;
		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		}
	}
	
	/**
	 * Metodo que edita el usuario + persona + persona contacto + rol
	 * @Params Usuario
	 * @Return void O AdministradorUserException
	 */
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public UsuarioDTO edithUserSystem(UsuarioDTO usuario) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.EDITH_USER_SYSTEM, EntityEnum.USUARIO, usuario,null));
		try {
			
			Optional<Usuario> userExist =  repository.findById(UsuarioPKMapper.mapper(usuario.getId())  );
			if(! userExist.isPresent()) {
				throw new AdministradorUserException(EntityEnum.USUARIO,MethodsAdminUSerEnum.EDITH_USER_SYSTEM, LayerEnum.LOGIC,
						ErrorConstantes.ERROR_USER_NO_EXIST_FOR_EDITH);
			}
			
			Usuario u = userComponent.BuildUSerForEdith(usuario);
			personaRepository.saveAndFlush(u.getPersona());
			String token = TokenGenerator.generateToken(u.getEmail());
			u.setTokenActivate(token);
			repository.saveAndFlush(u);
			
			//#######LOGIC ROL USUARIO########
				//BUSCAMOS EL ROL DEL USUARIO ANTERIOR Y LO ELIMINAMOS
				RolesUsuarioPK rolPktoFind = RolesUsuarioPK.builder()
						 .idSistema(usuario.getRol().getId().getIdSistema())
						 .idRolSistema(usuario.getRol().getId().getId())
						 .idTipoIdentificacion(u.getId().getIdTipoIdentificacion())
						 .numeroIdentificacion(u.getId().getNumeroIdentificacion()).idUsuario(u.getId().getId()).build();
				Optional<RolesUsuario> rolUsuarioExist = rolesUsuarioRepository.findById(rolPktoFind);
				if(rolUsuarioExist.isPresent()) {
					rolesUsuarioRepository.delete(rolUsuarioExist.get());
				}
				// ####################################################
				// CREAMOS UN NUEVO ROL
				RolesUsuarioPK rolPk = RolesUsuarioPK.builder().
						 idSistema(usuario.getRol().getId().getIdSistema())
						.idRolSistema(usuario.getRol().getId().getId()).nombreRol(usuario.getRol().getId().getNombreRol())
						.idTipoIdentificacion(u.getId().getIdTipoIdentificacion())
						.numeroIdentificacion(u.getId().getNumeroIdentificacion()).idUsuario(u.getId().getId()).build();
				RolesUsuario rol = RolesUsuario.builder().idPk(rolPk).build();
				rolesUsuarioRepository.saveAndFlush(rol);
				// #####################################################
			//##################################################
				
		    //#######LOGIC PERSONA CONTACTO ######
		   		if( Objects.nonNull(usuario.getPersona().getListPersonaContacto())  && usuario.getPersona().getListPersonaContacto().size() > 0 ) {
		   			PersonaContactoDTO personaContacto = usuario.getPersona().getListPersonaContacto().get(0);	
			   // BUSCAMOS SI TIENE DATOS DE CONTACTO Y LOS ELIMINAMOS
					PersonaContactoPK personaContactoPK = PersonaContactoPKMapper.mapper(personaContacto.getId()) ;
					Optional<PersonaContacto> personaContactoExis = personaContactoRepository.findById( personaContactoPK);
					if( personaContactoExis.isPresent()) {
						personaContactoRepository.delete(personaContactoExis.get());
					}
			   //###################################
			   // CREAMOS EL NUEVO DATO DE CONTACTO
					personaContactoRepository.save( PersonaContactoMapper.mapper(personaContacto));
			   //###################################
		      }
		    // #####################################
				

			
		   	logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.EDITH_USER_SYSTEM, EntityEnum.USUARIO, null,"PROCESO EXITOSO"));
			UsuarioDTO userU = UsuarioMapper.Mapper(u, false);
			return userU;
		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO,MethodsAdminUSerEnum.EDITH_USER_SYSTEM, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		}
	}

	
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public UsuarioDTO saveUserSystemPublic(UsuarioDTO usuario) throws AdministradorUserException {

		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.GET_USER_FOR_LOGIN, EntityEnum.USUARIO, usuario));
		try {

			Usuario u = userComponent.BuildUSerForSavePublic(usuario);

			personaRepository.saveAndFlush(u.getPersona());
			
			String token = TokenGenerator.generateToken(u.getEmail());
			u.setTokenActivate(token);

			repository.saveAndFlush(u);

			RolesUsuarioPK rolPk = RolesUsuarioPK.builder().idSistema(usuario.getRol().getId().getIdSistema())
					.idRolSistema(usuario.getRol().getId().getId()).nombreRol(usuario.getRol().getId().getNombreRol())
					.idTipoIdentificacion(u.getId().getIdTipoIdentificacion())
					.numeroIdentificacion(u.getId().getNumeroIdentificacion()).idUsuario(u.getId().getId()).build();

			RolesUsuario rol = RolesUsuario.builder().idPk(rolPk).build();

			rolesUsuarioRepository.save(rol);
			rolesUsuarioRepository.flush();
			
			UsuarioDTO userU = UsuarioMapper.Mapper(u, false);
			
			
			return userU;

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		}
	}
	
	
	/**
	 * Metodo para confirmar usuario despues de su registro
	 */
	public void confirmUser(UsuarioDTO usuario) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.USER_CONFIRM, EntityEnum.USUARIO, usuario));
		logger.info("Confirmando usuario ......");

		try {

			UsuarioPK usuarioPk = UsuarioPK.builder().id(usuario.getId().getId())
					.idTipoIdentificacion(usuario.getId().getIdTipoIdentificacion())
					.numeroIdentificacion(usuario.getId().getNumeroIdentificacion()).build();

			Optional<Usuario> usuarioE = this.repository.findById(usuarioPk);

			if (usuarioE.isPresent()) {
				
				Usuario u = usuarioE.get();
				
				if( u.getTokenActivate().equals(usuario.getTokenActivate())) {
					u.setActivo(true);
					u.setConfirmado(true);
					this.repository.save(u);
					this.repository.flush();
				}

			} else {
				throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.USER_CONFIRM,
						LayerEnum.LOGIC, ErrorConstantes.ERROR_INTENTAR_GUARDAR);

			}

		} catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException(EntityEnum.USUARIO, MethodsAdminUSerEnum.USER_CONFIRM, LayerEnum.LOGIC,
					ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		}

	}
	// =======================================================================
	// ----- FIN CUSTOM LOGIC -----
	// =======================================================================

}
