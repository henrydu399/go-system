package com.gosystem.parametricas.api.entitys;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "parametros")
public class Parametro  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String key;
	
	private String value;
	
	private String apiName;
	
	private String descripcion;
}
