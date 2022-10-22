package com.gosystem.home.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.gosystem.commons.adminUsers.dto.PrivilegiosRolUsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.enums.PrivilegioOperacion;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.UtilGson;



public class UtilPrivilegioMetodo {
	
	public static final String PRIVILEGIO = "privilegio";
	
	public static boolean isPrivilegioOk(HttpServletRequest req, PrivilegioOperacion privilegioOperacion) throws HomeException  {
		String temp = req.getHeader(PRIVILEGIO);
		PrivilegiosRolUsuarioDTO privilegio = UtilGson.getGson().fromJson(temp, PrivilegiosRolUsuarioDTO.class);
		try {
			switch (privilegioOperacion) {
			case buscar:
					if( !privilegio.isBuscar()) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
					}
				break;
			case crear:
				if( !privilegio.isCrear()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;
			case desactivar:
				if( !privilegio.isDesactivar()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;
			case editar:
				if( !privilegio.isEditar()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;
			case eliminar:
				if( !privilegio.isEliminar()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;
			case getAll:
				if( !privilegio.isGetAll()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;	
			case getNormal:
				if( !privilegio.isGetNormal()) {
					throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
				}
				break;
			default:
				break;
			}
			return true;
		
		} catch (Exception e) {
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_PRIVILEGIOS);
		}
	
	}

}
