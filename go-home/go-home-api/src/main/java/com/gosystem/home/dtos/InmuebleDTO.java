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
public class InmuebleDTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private long id;
private int area;
private int baños;
private int cantParqueaderos;
private String caracteristicas;
private String direccion;
private int estrato;
private Timestamp fechaCreacion;
private Date fechaModificacion;
private int habitaciones;
private Long idBarrio;
private Long idCiudad;
private Long idDepartamento;
private int idTipoIdentificacion;
private Long idTipoIdentificacionPropietario;
private Long idUsuario;
private Long idUsuarioPropietario;
private String numeroIdentificacion;
private String numeroIdentificacionPropietario;
private int precio;
private String ubicacion;
private TiposInmuebleDTO tiposInmueble;
private EstadoDTO estado;
private UsoInmuebleDTO usoInmueble;
private List<InmueblesSeguimientoDTO> inmueblesSeguimientos;

}