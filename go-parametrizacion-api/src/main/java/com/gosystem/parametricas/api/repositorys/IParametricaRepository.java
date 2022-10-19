package com.gosystem.parametricas.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gosystem.parametricas.api.entitys.Parametro;

public interface IParametricaRepository  extends JpaRepository<Parametro, String>{

}
