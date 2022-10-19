package co.system.out.emailservice.colp.dto;

import java.util.List;

public class EmailRequestDTO {  
	
private DestinatarioDTO destinatario;
private String asunto;
private String plantilla;
private String texto;
private List<ParametroDTO> parametro;
private List<ArchivoDTO> archivo;
private String remitente;
private Boolean esTextoHtml;
public DestinatarioDTO getDestinatario() {
	return destinatario;
}
public void setDestinatario(DestinatarioDTO destinatario) {
	this.destinatario = destinatario;
}
public String getAsunto() {
	return asunto;
}
public void setAsunto(String asunto) {
	this.asunto = asunto;
}
public String getPlantilla() {
	return plantilla;
}
public void setPlantilla(String plantilla) {
	this.plantilla = plantilla;
}
public String getTexto() {
	return texto;
}
public void setTexto(String texto) {
	this.texto = texto;
}
public List<ParametroDTO> getParametro() {
	return parametro;
}
public void setParametro(List<ParametroDTO> parametro) {
	this.parametro = parametro;
}
public List<ArchivoDTO> getArchivo() {
	return archivo;
}
public void setArchivo(List<ArchivoDTO> archivo) {
	this.archivo = archivo;
}
public String getRemitente() {
	return remitente;
}
public void setRemitente(String remitente) {
	this.remitente = remitente;
}
public Boolean getEsTextoHtml() {
	return esTextoHtml;
}
public void setEsTextoHtml(Boolean esTextoHtml) {
	this.esTextoHtml = esTextoHtml;
}





}
