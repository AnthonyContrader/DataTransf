package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.contrader.model.Conversion;

@Repository 
@Transactional //per automatizzare gli accessi al database.
public interface ConversionRepository extends CrudRepository<Conversion, Long> {
	
	@Query(value = "SELECT conversion.id_conversion FROM(SELECT id_conversion FROM conversion WHERE id_user=:userId ORDER BY id_conversion DESC LIMIT 1)conversion ORDER BY conversion.id_conversion", nativeQuery = true)
	Long getLastId(@Param("userId") Long userId);
	
	Iterable<Conversion> findAllByIdUser(Long idUser);
}
