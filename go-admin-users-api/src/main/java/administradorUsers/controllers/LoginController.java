package administradorUsers.controllers;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.Usuario;
import administradorUsers.services.UserService;
import administradorUsers.services.imp.RolesUsuarioServiceImpl;
import administradorUsers.utils.BCryptPasswordEncoder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RequestMapping("/login")
@RestController
public class LoginController {

	
	@Value("${jwt.key}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long timeExpiration;
	
	@Autowired
	UserService usuarioRepository;
	
	@Autowired
	RolesUsuarioServiceImpl rolesUsuarioServiceImpl;
	

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody Usuario userIn)  {    	
    		  	
    		return ResponseEntity.ok(true);
    	
    }
    
    
   

	
}