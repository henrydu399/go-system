package administradorUsers.enums;

public enum MethodsAdminUSerEnum  {
	
	GET_USER_FOR_LOGIN("GET_USER_FOR_LOGIN"),

	
	FIND_ROL_SISTEM_BY_SYSTEMA_NAME("FIND_ROL_SISTEM_BY_SYSTEMA_NAME");
	
	private String valueName;
	

	private MethodsAdminUSerEnum(String valueName) {
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
