package com.gosystem.commons.enums;

public enum MethodsEnum {
	
	INIT("INICIAR"),
	LOGIN("LOGIN"),
	SAVE("GUARDAR"),
	EDITH("EDITAR"),
	DELETE("BORRAR"),
	DESACTIVATE ("DESACTIVAR"),
	GETBYID("BUSCAR POR ID"),
	GETPARAMETRO("BUSCAR UN PARAMETRO"),
	GETALL ("TRAER TODO"),
	FIND("BUSCAR"),
	FIND_BY_ID("BUSCAR POR ID"),
	FIND_ALL("BUSCAR TODO"),
	FIND_CUSTOM("BUSCAR FILTRO"),
	SEND_EMAIL("EVIAR EMAIL"),
	INIT_PARAMETERS("CARGAR PARAMETROS"),
	MAPPER("MAPPER");
	
	
	
	private String valueName;
	

	private MethodsEnum(String valueName) {
		this.valueName = valueName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.valueName;
	}
	
	

}
