package co.system.out.emailservice.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;




public class juzDocumentoSendEmail implements Serializable {
	private static final long serialVersionUID = 1L;


	private String tdpIdDocumentoProceso;


	private String tdpNombreTipoDocumento;


	private String tdpUrlDocumento;
	
	
	


	/**
	 * @return the tdpUrlDocumento
	 */
	public String getTdpUrlDocumento() {
		return tdpUrlDocumento;
	}



	/**
	 * @param tdpUrlDocumento the tdpUrlDocumento to set
	 */
	public void setTdpUrlDocumento(String tdpUrlDocumento) {
		this.tdpUrlDocumento = tdpUrlDocumento;
	}






	public juzDocumentoSendEmail() {
	}

	public String getTdpIdDocumentoProceso() {
		return this.tdpIdDocumentoProceso;
	}

	public void setTdpIdDocumentoProceso(String tdpIdDocumentoProceso) {
		this.tdpIdDocumentoProceso = tdpIdDocumentoProceso;
	}

	public String getTdpNombreTipoDocumento() {
		return this.tdpNombreTipoDocumento;
	}

	public void setTdpNombreTipoDocumento(String tdpNombreTipoDocumento) {
		this.tdpNombreTipoDocumento = tdpNombreTipoDocumento;
	}

	

	

}