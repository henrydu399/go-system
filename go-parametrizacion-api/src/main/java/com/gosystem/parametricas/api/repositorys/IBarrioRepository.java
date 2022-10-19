package com.gosystem.parametricas.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gosystem.parametricas.api.entitys.Barrio;
import com.gosystem.parametricas.api.entitys.BarrioPK;

public interface IBarrioRepository extends JpaRepository<Barrio, BarrioPK> {

}
