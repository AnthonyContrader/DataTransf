package com.it.contrader.service;

import com.it.contrader.service.dto.ConvsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Convs.
 */
public interface ConvsService {

    /**
     * Save a convs.
     *
     * @param convsDTO the entity to save
     * @return the persisted entity
     */
    ConvsDTO save(ConvsDTO convsDTO);

    /**
     * Get all the convs.
     *
     * @return the list of entities
     */
    List<ConvsDTO> findAll();


    /**
     * Get the "id" convs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConvsDTO> findOne(Long id);

    /**
     * Delete the "id" convs.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<ConvsDTO> findAllByUsr(Long usr);
}
