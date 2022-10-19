package com.gosystem.home.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TiposInmuebleDTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer id;
private String nombre;
private List<InmuebleDTO> inmuebles;

}