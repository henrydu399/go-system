package jpaHelper.enums;

public enum ImportEnum {
	
	
	LOMBOK_ALLARGSCONSTRUCTOR("import lombok.AllArgsConstructor;"),
	LOMBOK_BUILDER("import lombok.Builder;"),
	LOMBOK_DATA("import lombok.Data;"),
	LOMBOK_NOARGSCONSTRUTOR("import lombok.NoArgsConstructor;"),
	
	JAVA_IO__SERIALIZABLE("import java.io.Serializable;"),
	
	JAVA_UTIL_LIST("import java.util.List;"),
	JAVA_UTIL_DATE("import java.util.Date;"),
	
	
	JAVA_SQL_TIMESTAMP("import java.sql.Timestamp;"),
	
	
	JAVAX_PERSISTENCE_ALL("import javax.persistence.*;");
	
	
	


	
	 private String value;
	 
	
	private ImportEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
