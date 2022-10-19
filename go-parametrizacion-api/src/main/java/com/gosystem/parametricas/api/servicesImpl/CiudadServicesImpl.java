package com.gosystem.parametricas.api.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadPKDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.parametricas.api.entitys.Ciudad;

import com.gosystem.parametricas.api.entitys.Departamento;
import com.gosystem.parametricas.api.enums.EntityParametrizacionEnum;
import com.gosystem.parametricas.api.enums.MethodsParametrizacionEnum;
import com.gosystem.parametricas.api.mappers.CiudadMapper;
import com.gosystem.parametricas.api.mappers.CiudadPKMapper;

import com.gosystem.parametricas.api.repositorys.ICiudadRepository;

import com.gosystem.parametricas.api.services.ICiudadService;

@Service
public class CiudadServicesImpl implements ICiudadService {

	
	@Autowired
	private ICiudadRepository ciudadRepository;
	
	private Logger logger;
	
	public CiudadServicesImpl() {
		this.logger = UtilsLogs.getLogger(DepartamentoServiceImpl.class.getName());
		
	}
	
	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void save(CiudadDTO ciudadDto) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityParametrizacionEnum.CIUDAD ,ciudadDto));
		try {
			if( Objects.isNull(ciudadDto)) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_DATOS_DEPARTAMENTO);
			}
			if(Objects.isNull(ciudadDto.getNombre()) || ciudadDto.getNombre().isEmpty()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}	
			Optional<Ciudad> d = ciudadRepository.findByNombre(ciudadDto.getNombre());
			if( d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_EXISTE_DEPARTAMENTO_CON_NOMBRE);
			}
			Ciudad ciudadEntity = CiudadMapper.mapper(ciudadDto);	
			ciudadRepository.save(ciudadEntity);
			logger.info("OPERACION REALIZADA EXITOSAMENTE");
			
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.SAVE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);

		}	
		
	}

	@Override
	@Transactional(rollbackFor = {ParametrizacionException.class , Exception.class } )
	public void edith(CiudadDTO ciudadDto) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityParametrizacionEnum.CIUDAD,ciudadDto));
		try {
			if( Objects.isNull(ciudadDto)) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_DATOS_DEPARTAMENTO);
			}
			if(Objects.isNull(ciudadDto.getNombre()) || ciudadDto.getNombre().isEmpty()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}
			if(Objects.isNull(ciudadDto.getId()) ) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NOMBRE_DEPARTAMENTO);
			}
			Optional<Ciudad> d = ciudadRepository.findById(CiudadPKMapper.mapper(ciudadDto.getId()));
			if( !d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NO_EXISTE_DEPARTAMENTO);
			}
			Ciudad ciudadEntity = CiudadMapper.mapper(ciudadDto);	
			ciudadRepository.save(ciudadEntity);
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.EDITH ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
		
	}

	@Override
	public List<CiudadDTO> getAll() throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.GETALL, EntityParametrizacionEnum.CIUDAD , null));
		try {
			List<Ciudad> listEntity = ciudadRepository.findAll();
			logger.info("OPERACION REALIZADA EXITOSAMENTE");
			return CiudadMapper.mapList(listEntity);
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.GETALL ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);

		}	
	}

	@Override
	public void delete(CiudadPKDTO id) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, EntityParametrizacionEnum.CIUDAD,id));
		try {
			Optional<Ciudad> d = ciudadRepository.findById(CiudadPKMapper.mapper(id));
			if( !d.isPresent()) {
				throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_NO_EXISTE_DEPARTAMENTO);
			}
			ciudadRepository.delete(d.get());
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsEnum.DELETE ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
		
	}

	@Override
	public void desactivate(CiudadPKDTO id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CiudadDTO getByid(CiudadPKDTO id) throws ParametrizacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CiudadDTO> getAllByIdDepartamento(long idDepartamento) throws ParametrizacionException {
		logger.info(UtilsLogs.getInfo(MethodsParametrizacionEnum.getAllByIdDepartamento, EntityParametrizacionEnum.CIUDAD,idDepartamento));
		List<CiudadDTO>  outList = null;
		try {
			Departamento d = Departamento.builder()
					.id(idDepartamento)
					.build();
			
			List<Ciudad> list = ciudadRepository.findByDepartamento(d);
			if(  Objects.nonNull(list) && list.size() > 0) {
				outList = new ArrayList<CiudadDTO>();
				outList = CiudadMapper.mapList(list);
			}	
			logger.info("OPERACION REALIZADA EXITOSAMENTE");	
			return outList;
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException(EntityParametrizacionEnum.CIUDAD, MethodsParametrizacionEnum.getAllByIdDepartamento ,LayerEnum.SERVICE , ErrorConstantes.GO_PARAMETRIZACION_API_ERROR_GUARDANDO_DEPARTAMENTO);
		}	
	}

}
