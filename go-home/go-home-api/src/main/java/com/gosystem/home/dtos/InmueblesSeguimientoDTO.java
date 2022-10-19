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
public class InmueblesSeguimientoDTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long id;
private Timestamp fechaCreacion;
private Date fechaLimite;
private Date fechaModificacion;
private Integer idTipoIdentificacionCliente;
private Integer idTipoIdentificacionUsuario;
private Long idUsuario;
private Long idUsuarioCliente;
private String numeroIdentificacionCliente;
private String numeroIdentificacionUsuario;
private InmuebleDTO inmueble;
private EstadoDTO estado;
private List<InmuebleSeguimientoDetalleDTO> inmuebleSeguimientoDetalles;

}