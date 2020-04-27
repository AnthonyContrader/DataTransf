package com.it.contrader.service.impl;

import com.it.contrader.service.ChangesService;
import com.it.contrader.domain.Changes;
import com.it.contrader.repository.ChangesRepository;
import com.it.contrader.service.dto.ChangesDTO;
import com.it.contrader.service.mapper.ChangesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Changes.
 */
@Service
@Transactional
public class ChangesServiceImpl implements ChangesService {

    private final Logger log = LoggerFactory.getLogger(ChangesServiceImpl.class);

    private final ChangesRepository changesRepository;

    private final ChangesMapper changesMapper;

    public ChangesServiceImpl(ChangesRepository changesRepository, ChangesMapper changesMapper) {
        this.changesRepository = changesRepository;
        this.changesMapper = changesMapper;
    }

    /**
     * Save a changes.
     *
     * @param changesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChangesDTO save(ChangesDTO changesDTO) {
        log.debug("Request to save Changes : {}", changesDTO);
        Changes changes = changesMapper.toEntity(changesDTO);
        changes = changesRepository.save(changes);
        return changesMapper.toDto(changes);
    }

    /**
     * Get all the changes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ChangesDTO> findAll() {
        log.debug("Request to get all Changes");
        return changesRepository.findAll().stream()
            .map(changesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one changes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChangesDTO> findOne(Long id) {
        log.debug("Request to get Changes : {}", id);
        return changesRepository.findById(id)
            .map(changesMapper::toDto);
    }

    /**
     * Delete the changes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Changes : {}", id);
        changesRepository.deleteById(id);
    }
    
    @Override
	public List<ChangesDTO> findAllByUser(Long id) {
		 log.debug("Request to get all Changes");
	        return changesRepository.findAllByUser(id).stream()
	            .map(changesMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	}
}
