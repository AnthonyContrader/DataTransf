package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Conversion;

@Repository 
@Transactional //per automatizzare gli accessi al database.
public interface ConversionRepository extends CrudRepository<Conversion, Long> {
		
	Iterable<Conversion> findAllByIdUser(Long idUser);
}
