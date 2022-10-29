package administradorUsers.enums;

public enum MethodsAdminUSerEnum  {
	
	GET_USER_FOR_LOGIN("GET_USER_FOR_LOGIN"),
	EDITH_USER_SYSTEM("EDITH_USER_SYSTEM"),

	
	FIND_ROL_SISTEM_BY_SYSTEMA_NAME("FIND_ROL_SISTEM_BY_SYSTEMA_NAME"),
	
	USER_CONFIRM ("USER_CONFIRM");
	
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
