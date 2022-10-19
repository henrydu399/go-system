package com.gosystem.parametricas.api.servicesImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.parametricas.api.entitys.Departamento;
import com.gosystem.parametricas.api.enums.EntityParametrizacionEnum;
import com.gosystem.parametricas.api.mappers.DepartamentoMapper;
import com.gosystem.parametricas.api.repositorys.IDepartamentoRepository;
import com.gosystem.parametricas.api.services.IDepartamentoService;



@Service
public class DepartamentoServiceImpl implements IDepartamentoService{
	
	@Autowired
	private IDepartamentoRepository departamentoRepository;
	
	private Logger logger;
	
	public DepartamentoServiceImpl() {
		this.logger = UtilsLogs.getLogger(DepartamentoServiceImpl.class.getName());
		
	}
	
	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void save(DepartamentoDTO departamento) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityParametrizacionEnum.DEPARTAMENTO ,departamento));
		try {
			if( Objects.isNull(departamento)) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_DATOS_DEPARTAMENTO);
			}
			if(Objects.isNull(departamento.getNombre()) || departamento.getNombre().isEmpty()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}	
			Optional<Departamento> d = departamentoRepository.findByNombre(departamento.getNombre());
			if( d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_EXISTE_DEPARTAMENTO_CON_NOMBRE);
			}
			Departamento departamentoEntity = DepartamentoMapper.map(departamento);	
			departamentoRepository.save(departamentoEntity);
			logger.info("OPERACION REALIZADA EXITOSAMENTE");
			
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);

		}	
	}

	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void edith(DepartamentoDTO departamento) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityParametrizacionEnum.DEPARTAMENTO,departamento));
		try {
			if( Objects.isNull(departamento)) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_DATOS_DEPARTAMENTO);
			}
			if(Objects.isNull(departamento.getNombre()) || departamento.getNombre().isEmpty()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}
			if(Objects.isNull(departamento.getId()) ) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}
			Optional<Departamento> d = departamentoRepository.findById(departamento.getId());
			if( !d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NO_EXISTE_DEPARTAMENTO);
			}
			Departamento departamentoEntity = DepartamentoMapper.map(departamento);
			departamentoRepository.save(departamentoEntity);
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
		
	}

	@Override
	public List<DepartamentoDTO> getAll() throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.GETALL, EntityParametrizacionEnum.DEPARTAMENTO , null));
		try {
			List<Departamento> listEntity = departamentoRepository.findAll();
			logger.info("OPERACION REALIZADA EXITOSAMENTE");
			return DepartamentoMapper.mapList(listEntity);
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.GETALL ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);

		}	
	}

	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void delete(long id) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, EntityParametrizacionEnum.DEPARTAMENTO,id));
		try {
	
			Optional<Departamento> d = departamentoRepository.findById(id);
			if( !d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NO_EXISTE_DEPARTAMENTO);
			}
			departamentoRepository.delete(d.get());
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
		
	}

	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void desactivate(long id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DepartamentoDTO getByid(long id) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, EntityParametrizacionEnum.DEPARTAMENTO,id));
		try {
			Optional<Departamento> d = departamentoRepository.findById(id);
			if( !d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NO_EXISTE_DEPARTAMENTO);
			}
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
			return DepartamentoMapper.map(d.get());
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.DEPARTAMENTO, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
	}

}
