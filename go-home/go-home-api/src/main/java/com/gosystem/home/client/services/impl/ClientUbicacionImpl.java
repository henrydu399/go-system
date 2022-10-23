package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.BarrioDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.commons.parametrizacion.dto.ParametroDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IParametrizacionClientService;
import com.gosystem.home.client.services.IUbicacionClienteService;
import com.gosystem.home.enums.EntityHomeEnum;

@Service
public class ClientUbicacionImpl implements IUbicacionClienteService{

	
	private Logger logger;
	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
	//PATHS //
	private String urlGateway;
	private String pathDepartamento;
	private String pathCiudad;
	private String pathBarrio;
	//
	
	private RestTemplate restTemplate;
	
	
	@PostConstruct
	public void init() throws ParametrizacionException {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTE ENVIO CORREOS");
		try {
			this. urlGateway       = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
			this. pathDepartamento = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_PARAMETRICAS_API_DEPARTAMENTO);
			this. pathCiudad       = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_PARAMETRICAS_API_CIUDAD);
			this. pathBarrio       = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_PARAMETRICAS_API_BARRIO);
			logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
			if(Objects.isNull(urlGateway)  ||  Objects.isNull(pathDepartamento) || Objects.isNull(pathCiudad) || Objects.isNull(pathBarrio)) {
				this.throwReturn();
			}
		}catch (ParametrizacionException e) {
			   this.throwReturn();
		}	
	}
	
	private void throwReturn() throws ParametrizacionException{
		throw new ParametrizacionException(null, MethodsEnum.INIT_PARAMETERS, LayerEnum.SERVICE, "No se ha podico cargar los parametros :" 
				+ " URL_GATEWAY"
				+ "PATH_GO_PARAMETRICAS_API_DEPARTAMENTO"
				+ "PATH_GO_PARAMETRICAS_API_CIUDAD"
				+ "PATH_GO_PARAMETRICAS_API_BARRIO"
				);	
	}
	
	
	
	public ClientUbicacionImpl() {
		logger = UtilsLogs.getLogger(ClientParametrizacionServiceImpl.class.getName());
	}
	
	
	@Override
	public List<DepartamentoDTO> getAllDepartamentos() throws HomeException {
		logger.info("METODO : getAllDepartamentos() ->Consultando departamentos");	
		try {
				this.restTemplate = new RestTemplate();
				URI uri = new URI(this.urlGateway + this. pathDepartamento);
				ResponseEntity<DepartamentoDTO[]> response = restTemplate.getForEntity( uri, DepartamentoDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					List<DepartamentoDTO> list = Arrays.asList(response.getBody()) ;
					logger.info("METODO : getAllDepartamentos() ->Consultados correctamente");
					return list;
				} else {
					logger.info("METODO : getAllDepartamentos() ->Error consultando ");
					throw new ParametrizacionException( EntityHomeEnum.DEPARTAMENTO, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
		}	
	}

	@Override
	public List<CiudadDTO> getAllCiudades() throws HomeException {
		logger.info("METODO : getAllCiudades() -> Consultando ciudades ");	
		try {
				this.restTemplate = new RestTemplate();
				URI uri = new URI(this.urlGateway + this.pathCiudad);
				ResponseEntity<CiudadDTO[]> response = restTemplate.getForEntity( uri, CiudadDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					List<CiudadDTO> list = Arrays.asList(response.getBody()) ;
					logger.info("METODO : getAllCiudades() ->Consultadas correctamente");
					return list;
				} else {
					logger.info("METODO : getAllCiudades() ->Error Consultando ");
					throw new ParametrizacionException( EntityHomeEnum.CIUDAD, MethodsEnum.GETALL, LayerEnum.CLIENT , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_CARGANDO_CIUDADES);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityHomeEnum.CIUDAD, MethodsEnum.GETALL, LayerEnum.CLIENT , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_CARGANDO_CIUDADES);
		}	
	}

	@Override
	public List<BarrioDTO> getAllBarrios() throws HomeException {
		logger.info("METODO : getAllBarrios() -> Consultando ciudades ");	
		try {
				this.restTemplate = new RestTemplate();
				URI uri = new URI(this.urlGateway + this.pathBarrio);
				ResponseEntity<BarrioDTO[]> response = restTemplate.getForEntity( uri, BarrioDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					List<BarrioDTO> list = Arrays.asList(response.getBody()) ;
					logger.info("METODO : getAllBarrios() ->Consultadas correctamente");
					return list;
				} else {
					logger.info("METODO : getAllBarrios() ->Error Consultando ");
					throw new ParametrizacionException( EntityHomeEnum.DEPARTAMENTO, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_CARGANDO_BARRIOS);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_CARGANDO_BARRIOS);
		}	
	}

	@Override
	public BarrioDTO findBarrioByName(String barrioName) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CiudadDTO> getAllCiudadesByIdDepartamento(long idDepartamento) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CiudadDTO> getAllCiudadesByNameDepartamento(String nameDepartamento) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BarrioDTO> getAllBarriosByIdCiudad(long idCiudad) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
