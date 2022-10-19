package com.gosystem.commons.exceptions;

import java.util.Objects;

import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException.AdministradorUserExceptionDto;
import com.gosystem.commons.utils.UtilGson;

public class HomeException extends RuntimeException{
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 
	 //private String tecnicalError;
	// private String codeError;
	 
	 private AdministradorUserExceptionDto data;
	 private String mensaje;
	
	
	

	/*
	 * Custom contrutor for aplicatiom
	 */
	public HomeException(Enum<?>  _entidad ,Enum<?> _operacion , LayerEnum _layer  , String _mensaje ) {
		data = new AdministradorUserExceptionDto();
		this.data.layer = _layer;
		this.data.message = _mensaje;
		if(Objects.nonNull(_entidad)) {
			this.data.entidad = _entidad.name();	
		}
		if(Objects.nonNull(_operacion)) {
		this.data.operacion = _operacion.name();	
		}
			//this.message = "[MENSAJE] : "+this.buildMensaje(mensaje)+"   [ENTIDAD] : "+ entidad .name() + "  [OPERACION] : "+ operacion.getValueName() + " [CAPA]  : "+ capa.name() + "";
		

	}
	
	

	
	@Override
    public String getMessage() {
    	return UtilGson.SerializeObjet(data);
    }

	
	
	public String getMensaje() {
		return UtilGson.SerializeObjet(data);
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public AdministradorUserExceptionDto getData() {
		return data;
	}


	public void setData(AdministradorUserExceptionDto data) {
		this.data = data;
	}









	public static class AdministradorUserExceptionDto {
		  String message;
		  LayerEnum layer;
		  String entidad;
		  String operacion;
		  String status;
	}


}
