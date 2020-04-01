package it.contrader.dao;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.contrader.model.Changes;

@Repository
@Transactional
public interface ChangesRepository extends CrudRepository<Changes, Long> {

	@Query(value = "SELECT changes.id FROM(SELECT id FROM changes WHERE user=:userId ORDER BY id DESC LIMIT 1)changes ORDER BY changes.id", nativeQuery = true)
	Long getLastId(@Param("userId") Long userId);
	
}
