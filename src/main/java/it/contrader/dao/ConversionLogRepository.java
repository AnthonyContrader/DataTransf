package it.contrader.dao;

import it.contrader.model.Conversion;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 	@Repository
	@Transactional
	public interface ConversionLogRepository extends CrudRepository<Conversion, Long> {
	
 		Iterable<Conversion> findAllByIdUser(Long idUser);
	}

 	
