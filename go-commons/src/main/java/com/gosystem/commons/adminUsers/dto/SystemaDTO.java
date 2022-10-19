package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	private String nombre;

	

}