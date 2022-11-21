package administradorUsers.components;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaPK;
import administradorUsers.entitys.Usuario;
import administradorUsers.entitys.UsuarioPK;
import administradorUsers.mappers.PersonaSistemaMapper;
import administradorUsers.repository.IPersonaRepository;
import administradorUsers.repository.IUsuariosRepository;

@Component
public class UserComponent {
	
	@Autowired
	private IUsuariosRepository userRepository;
	
	@Autowired
	private IPersonaRepository personaRepository;
	

	@Value("${id.tipo.identificacion.nan}")
	private String idTipoIdentificacion;
	
	
	public Usuario BuildUSerForSavePublic(UsuarioDTO in) {
		
		Long numeroidentificacion = userRepository.findMaxNumeroIdentificacion();
		if( Objects.isNull(numeroidentificacion) ) {
			numeroidentificacion = Long.valueOf(1);
		}			
		PersonaPK pkPersona = PersonaPK.builder()
				.idTipoIdentificacion(Integer.parseInt(idTipoIdentificacion))
				.numeroIdentificacion(String.valueOf(numeroidentificacion.longValue())  )
				.build();
				
		Persona p = Persona.builder()
				.id(pkPersona)
				.nombres(in.getPersona().getNombres())
				.build();
		
		long idUsuario= userRepository.findUltimoId();
		
		UsuarioPK usuarioPk =  new UsuarioPK();
		usuarioPk.setId(idUsuario);
		usuarioPk.setIdTipoIdentificacion( pkPersona.getIdTipoIdentificacion());
		usuarioPk.setNumeroIdentificacion(pkPersona.getNumeroIdentificacion());
		
		Usuario u = new Usuario();
		u.setId(usuarioPk);
		u.setPersona(p);
		u.setPassword(in.getPassword());
		u.setEmail( in.getEmail());		
		return u;
				

		
		}
	
	
	
	public Usuario BuildUSerForSave(UsuarioDTO in) {
		
		
		PersonaPK pkPersona = PersonaPK.builder()
				.idTipoIdentificacion(in.getPersona().getId().getIdTipoIdentificacion())
				.numeroIdentificacion(in.getPersona().getId().getNumeroIdentificacion())
				.build();
				
		Persona p = Persona.builder()
				 .id(pkPersona)
				 .nombres(in.getPersona().getNombres())
				.apellidos(in.getPersona().getApellidos())
				.edad(in.getPersona().getEdad())
				.estadoCivil(in.getPersona().getEstadoCivil())
				.fechaNacimiento(in.getPersona().getFechaNacimiento())
				.nivelEscolaridad(in.getPersona().getNivelEscolaridad())
				.profesion(in.getPersona().getProfesion())
				.sexo(in.getPersona().getSexo())
				//.personaSistema( PersonaSistemaMapper.mapper(in.getPersona().getPersonaSistema()) )
				.build();
		
		long idUsuario= userRepository.findUltimoId();
		
		UsuarioPK usuarioPk =  new UsuarioPK();
		usuarioPk.setId(idUsuario);
		usuarioPk.setIdTipoIdentificacion( pkPersona.getIdTipoIdentificacion());
		usuarioPk.setNumeroIdentificacion(pkPersona.getNumeroIdentificacion());
		
		Usuario u = new Usuario();
		u.setId(usuarioPk);
		u.setPersona(p);
		u.setMovil(in.getMovil());
		u.setPassword(in.getPassword());
		u.setEmail( in.getEmail());		
		u.setActivo( in.isActivo());
		u.setConfirmado(in.isConfirmado());
		return u;
					
		}

	public Usuario BuildUSerForEdith(UsuarioDTO in) {
		
		
		PersonaPK pkPersona = PersonaPK.builder()
				.idTipoIdentificacion(in.getPersona().getId().getIdTipoIdentificacion())
				.numeroIdentificacion(in.getPersona().getId().getNumeroIdentificacion())
				.build();
				
		Persona p = Persona.builder()
				 .id(pkPersona)
				 .nombres(in.getPersona().getNombres())
				.apellidos(in.getPersona().getApellidos())
				.edad(in.getPersona().getEdad())
				.estadoCivil(in.getPersona().getEstadoCivil())
				.fechaNacimiento(in.getPersona().getFechaNacimiento())
				.nivelEscolaridad(in.getPersona().getNivelEscolaridad())
				.profesion(in.getPersona().getProfesion())
				.sexo(in.getPersona().getSexo())
				//.personaSistema( PersonaSistemaMapper.mapper(in.getPersona().getPersonaSistema()) )
				.build();
		

		
		UsuarioPK usuarioPk =  new UsuarioPK();
		usuarioPk.setId(in.getId().getId());
		usuarioPk.setIdTipoIdentificacion( pkPersona.getIdTipoIdentificacion());
		usuarioPk.setNumeroIdentificacion(pkPersona.getNumeroIdentificacion());
		
		Usuario u = new Usuario();
		u.setId(usuarioPk);
		u.setPersona(p);
		u.setMovil(in.getMovil());
		u.setPassword(in.getPassword());
		u.setEmail( in.getEmail());		
		u.setActivo( in.isActivo());
		u.setConfirmado(in.isConfirmado());
		return u;
					
		}


}
