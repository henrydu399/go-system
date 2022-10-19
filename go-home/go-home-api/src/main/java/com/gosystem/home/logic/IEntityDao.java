package com.gosystem.home.logic;


import java.util.List;

import com.gosystem.commons.exceptions.HomeException;




public interface IEntityDao<T> {
	
	List<T> getAll ()throws HomeException;
	
	void  save(T u)  throws HomeException;
	
	void  edith(T p) throws HomeException;
	
	T  find(T p) throws HomeException;
	
	List<T>  findAll(T p) throws HomeException;
	
	void  delete(T p) throws HomeException;
		
	
	
	

}
