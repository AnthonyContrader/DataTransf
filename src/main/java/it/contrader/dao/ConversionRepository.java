package it.contrader.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Conversion;

@Repository
@Transactional
public interface ConversionRepository extends CrudRepository<Conversion, Long> {



}
