package com.it.contrader.repository;

import com.it.contrader.domain.Changes;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Changes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChangesRepository extends JpaRepository<Changes, Long> {

	List<Changes> findAllByUser(Long id);
	
}
