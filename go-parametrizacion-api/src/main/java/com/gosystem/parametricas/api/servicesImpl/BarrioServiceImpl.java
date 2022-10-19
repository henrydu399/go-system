package com.gosystem.parametricas.api.servicesImpl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.BarrioDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.parametricas.api.entitys.Barrio;
import com.gosystem.parametricas.api.entitys.BarrioPK;
import com.gosystem.parametricas.api.enums.EntityParametrizacionEnum;
import com.gosystem.parametricas.api.mappers.BarrioMapper;
import com.gosystem.parametricas.api.repositorys.IBarrioRepository;
import com.gosystem.parametricas.api.services.IBarrioService;

@Service
public class BarrioServiceImpl implements IBarrioService{
	
	
	
	@Autowired
	private IBarrioRepository barrioRepository;
	
	private Logger logger;
	
	public BarrioServiceImpl() {
		this.logger = UtilsLogs.getLogger(BarrioServiceImpl.class.getName());
		
	}

	@Override
	public void save(BarrioDTO departamento) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edith(BarrioDTO departamento) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BarrioDTO> getAll() throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.GETALL, EntityParametrizacionEnum.BARRIO , null));
		try {
			List<Barrio> listEntity = this.barrioRepository.findAll();
			logger.info("OPERACION REALIZADA EXITOSAMENTE");
			return BarrioMapper.mapperListAsDto(listEntity);
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.BARRIO, MethodsEnum.GETALL ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_CARGANDO_CIUDADES);

		}	
	}

	@Override
	public void delete(BarrioPK id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desactivate(BarrioPK id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CiudadDTO getByid(BarrioPK id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BarrioDTO> getAllByIdDepartamento(long idDepartamento) throws ParametrizacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BarrioDTO> getAllByIdCiudad(long idDepartamento) throws ParametrizacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BarrioDTO> getAllByCustom(long idDepartamento, long idCiudad) throws ParametrizacionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
