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
	
		
		
		/*
		 * UsuarioPK usuarioPk = UsuarioPK.builder()
		 * .idTipoIdentificacion(pkPersona.getIdTipoIdentificacion())
		 * .numeroIdentificacion(pkPersona.getNumeroIdentificacion()) .build();
		 * 
		 * Usuario u = Usuario.builder() .id(usuarioPk) .persona(p)
		 * .email(in.getEmail()) .password(in.getPassword()) .build();
		 */
		
		return u;
				
				
		}


}
