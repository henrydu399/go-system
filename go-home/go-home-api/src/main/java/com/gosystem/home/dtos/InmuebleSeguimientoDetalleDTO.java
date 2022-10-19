package com.gosystem.home.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InmuebleSeguimientoDetalleDTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long id;
private Timestamp fechaCreacion;
private Date fechaLimite;
private String observaciones;
private EstadoDTO estado;
private InmueblesSeguimientoDTO inmueblesSeguimiento;
private TipoSeguimientoDTO tipoSeguimiento;

}