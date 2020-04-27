package com.it.contrader.repository;

import com.it.contrader.domain.Convs;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Convs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConvsRepository extends JpaRepository<Convs, Long> {

	List<Convs> findAllByUsr(Long usr);
	
}
