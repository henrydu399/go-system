package co.system.out.emailservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	
	@Column(name = "")
	private Date fechaCreacion;
	
	private String email;
	
	private String password;
	
	private String server;
	
	private String port;
	
	private String protocole;
	
	private boolean ttlsEnable;
	
	private boolean sslEnable;
	
	private boolean authEnable;
	
	private boolean socketFactoryFallback;
	
	
	
	
	

}
