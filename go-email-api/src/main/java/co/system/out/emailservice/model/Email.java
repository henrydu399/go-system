package co.system.out.emailservice.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emails")
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	
	private Date fechaCreacion;
	
	private Date fechaEnvio;
		
	
	private String sistema;
	
	
	private String status;
	
	
	private String errorTecnico;
	
	private String de;
	
	private String para;
	
	private String cuerpo;
	
	private String asunto;
	
	private String plantilla;
	
	private boolean isHtml;
	
	private String parameters;
	
	private String destinatarios;
	
	@Transient
	private List<String> listDestinatarios;
	
	
	

	
	
	

}
