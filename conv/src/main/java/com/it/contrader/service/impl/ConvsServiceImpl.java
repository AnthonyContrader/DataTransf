package com.it.contrader.service.impl;

import com.it.contrader.service.ConvsService;
import com.it.contrader.domain.Convs;
import com.it.contrader.repository.ConvsRepository;
import com.it.contrader.service.dto.ConvsDTO;
import com.it.contrader.service.mapper.ConvsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Convs.
 */
@Service
@Transactional
public class ConvsServiceImpl implements ConvsService {

    private final Logger log = LoggerFactory.getLogger(ConvsServiceImpl.class);

    private final ConvsRepository convsRepository;

    private final ConvsMapper convsMapper;

    public ConvsServiceImpl(ConvsRepository convsRepository, ConvsMapper convsMapper) {
        this.convsRepository = convsRepository;
        this.convsMapper = convsMapper;
    }

    /**
     * Save a convs.
     *
     * @param convsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConvsDTO save(ConvsDTO convsDTO) {
        log.debug("Request to save Convs : {}", convsDTO);
        Convs convs = convsMapper.toEntity(convsDTO);
        convs = convsRepository.save(convs);
        return convsMapper.toDto(convs);
    }

    /**
     * Get all the convs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConvsDTO> findAll() {
        log.debug("Request to get all Convs");
        return convsRepository.findAll().stream()
            .map(convsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one convs by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConvsDTO> findOne(Long id) {
        log.debug("Request to get Convs : {}", id);
        return convsRepository.findById(id)
            .map(convsMapper::toDto);
    }

    /**
     * Delete the convs by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Convs : {}", id);
        convsRepository.deleteById(id);
    }
    
    public List<ConvsDTO> findAllByUsr(Long usr){
    	return convsRepository.findAllByUsr(usr).stream().map(convsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    };
}
