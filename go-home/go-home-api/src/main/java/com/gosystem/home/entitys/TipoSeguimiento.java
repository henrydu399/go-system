package com.gosystem.home.entitys;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="TIPO_SEGUIMIENTO")
@NamedQuery(name="TipoSeguimiento.findAll", query="SELECT t FROM TipoSeguimiento t")
public class TipoSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to InmuebleSeguimientoDetalle
	@JsonIgnore
	@OneToMany(mappedBy="tipoSeguimiento")
	private List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles;

	

}