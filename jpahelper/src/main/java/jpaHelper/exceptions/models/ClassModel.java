package jpaHelper.exceptions.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassModel {
	
	private String className;
	private String paquete;
	private List<PropertieModel> listProperties;
	private List<String> listImplements;
	private List<String> listImports;
	

}
