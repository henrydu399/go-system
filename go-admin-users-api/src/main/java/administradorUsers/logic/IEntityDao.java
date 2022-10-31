package administradorUsers.logic;


import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Usuario;





public interface IEntityDao<T> {
	
	List<T> getAll ()throws AdministradorUserException;
	
	void  save(T u)  throws AdministradorUserException;
	
	void  edith(T p) throws AdministradorUserException;
	
	T  find(T p) throws AdministradorUserException;
	
	List<T>  findAll(T p) throws AdministradorUserException;
	
	void  delete(T p) throws AdministradorUserException;
	
	public void desactivate(Usuario usuario) throws AdministradorUserException ;
		
	
	
	

}
