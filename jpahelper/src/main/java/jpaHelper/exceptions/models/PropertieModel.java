package jpaHelper.exceptions.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertieModel {
	
	private String acceso;
	private String nombre;
	private String tipo;

}
