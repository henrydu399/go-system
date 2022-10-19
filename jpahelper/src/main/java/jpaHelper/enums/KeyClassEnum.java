package jpaHelper.enums;

import java.util.List;




public enum KeyClassEnum {
	
	PUBLIC("public"),
	PRIVATE ("private"),
	
	CLASS("class"),
	PACKAGE ("package"),
	
	ABSTRACT ("abstract"),
	
	
	IMPORT("import"),
	
	//COMENTARIOS
	COMENTARIO_A("/**"),
	COMENTARIO_B("*"),
	COMENTARIO_C("*/"),
	COMENTARIO_D("//"),
	///
	
	
	IMPLEMENTS("implements"),
	
	SERIALIZABLE("Serializable"),
	
	SERIALVERSIONUID("serialVersionUID"),
	

	SIMBOLOS_UNO ("})"),
	SIMBOLOS_DOS( "({"),
	SIMBOLOS_TRES ("}"),
	SIMBOLOS_CUATRO ("{"),
	

	ENTITY("@Entity"),
	TABLE("@Table"),
	ID("@Id"),
	NAMEDQUERY("@NamedQuery"),
	MANYTOONE("@ManyToOne"),
	JOINCOLUMNS("@JoinColumns"),
	JOINCOLUMN("@JoinColumn"),
	EMBEDDEDID("@EmbeddedId"),
	COLUMN("@Column"),
	EMBEDDABLE("@Embeddable"),
	
	TEMPORAL("@Temporal"),
	
	
	JSON("@JsonIgnore"),
	ONETOMANY("@OneToMany"),

	

	
	THIS("this"),
	RETURN("return"),
	
	
    GET("get"),
    SET("set"),
	

	LOMBOK_DATA("@Data"),
	LOMBOK_BUILDER("@Builder"),
	LOMBOK_ALLARGSCONSTRUCTOR("@AllArgsConstructor"),
	LOMBOK_NOARGSCONSTRUTOR("@NoArgsConstructor"),
	LOMBOK_GETTER("@Getter"),
	LOMBOK_SETTER("@Setter"),
	

	

	TYPE_string("String"),
	TYPE_byte("byte"),
	TYPE_short("short"),	
	TYPE_int("int"),
	TYPE_long("long"),
	TYPE_float("float"),
	TYPE_double("double"),
	TYPE_boolean("boolean"),
	TYPE_char("char"),
	TYPE_List("List"),
	
	type_date("Date"),
	type_Timestamp("Timestamp"),
	
	
	TYPE_Byte("Byte"),
	TYPE_Short("Short"),	
	TYPE_Integer("Integer"),
	TYPE_Long("Long"),
	TYPE_Float("Float"),
	TYPE_Double("Double"),
	TYPE_Boolean("Boolean"),
	TYPE_Char("Char");

	
	
	
	 private String value;
	 
	 

	private KeyClassEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	 
	

}
