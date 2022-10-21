package com.gosystem.commons.emailapi.dto;


import java.util.List;
import java.util.Map;



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
public class EmailDto {
	
	
    private String id;
	
	private String para;
	
	private String asunto;
	
	private String cuerpo;
	
	private String de;
	
	private String sistema;
	
	private String plantilla;
	
	private boolean isHtml;
	
	private Map<String, String> parameters;
	
	private String parametersString;
	
	private int intentos;
	
	
	
	private List<String> destinatareos;
	
	private List<String> listDestinatarios;
	

	
	
	
	
	
	

}
