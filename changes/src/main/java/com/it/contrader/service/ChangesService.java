package com.it.contrader.service;

import com.it.contrader.service.dto.ChangesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Changes.
 */
public interface ChangesService {

    /**
     * Save a changes.
     *
     * @param changesDTO the entity to save
     * @return the persisted entity
     */
    ChangesDTO save(ChangesDTO changesDTO);

    /**
     * Get all the changes.
     *
     * @return the list of entities
     */
    List<ChangesDTO> findAll();


    /**
     * Get the "id" changes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ChangesDTO> findOne(Long id);

    /**
     * Delete the "id" changes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<ChangesDTO> findAllByUser(Long id);
}
