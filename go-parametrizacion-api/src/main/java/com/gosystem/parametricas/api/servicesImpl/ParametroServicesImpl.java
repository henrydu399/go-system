package com.gosystem.parametricas.api.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.parametricas.api.entitys.Parametro;
import com.gosystem.parametricas.api.repositorys.IParametricaRepository;
import com.gosystem.parametricas.api.services.IParametrosService;




@Service
public class ParametroServicesImpl  implements IParametrosService{

	@Autowired
	private IParametricaRepository parametricaRepository;
	
	private Logger logger;
	
	
	
	public ParametroServicesImpl() {
		logger = UtilsLogs.getLogger(ParametroServicesImpl.class.getName());
	}
	
	@Override
	public Parametro getParametro(String key) throws ParametrizacionException {
		Parametro p= null;
		try {
			Optional<Parametro> parametroOpc =  parametricaRepository.findById(key);
			if( parametroOpc.isPresent()) {
				p = parametroOpc.get();
			}else {
				throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.NO_EXISTEN_REGISTROS);
			}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		return p;
		
		
	}

	
	public List<Parametro> getAll() throws ParametrizacionException {
		List<Parametro> list =  null;
		try {
			 list =  parametricaRepository.findAll();
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		return list;
	}

	
}
