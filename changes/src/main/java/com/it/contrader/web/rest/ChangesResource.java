package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.ChangesService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.service.dto.ChangesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Changes.
 */
@RestController
@RequestMapping("/api")
public class ChangesResource {

    private final Logger log = LoggerFactory.getLogger(ChangesResource.class);

    private static final String ENTITY_NAME = "changes";

    private final ChangesService changesService;

    public ChangesResource(ChangesService changesService) {
        this.changesService = changesService;
    }

    /**
     * POST  /changes : Create a new changes.
     *
     * @param changesDTO the changesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new changesDTO, or with status 400 (Bad Request) if the changes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/changes")
    @Timed
    public ResponseEntity<ChangesDTO> createChanges(@Valid @RequestBody ChangesDTO changesDTO) throws URISyntaxException {
        log.debug("REST request to save Changes : {}", changesDTO);
        if (changesDTO.getId() != null) {
            throw new BadRequestAlertException("A new changes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChangesDTO result = changesService.save(changesDTO);
        return ResponseEntity.created(new URI("/api/changes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /changes : Updates an existing changes.
     *
     * @param changesDTO the changesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated changesDTO,
     * or with status 400 (Bad Request) if the changesDTO is not valid,
     * or with status 500 (Internal Server Error) if the changesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/changes")
    @Timed
    public ResponseEntity<ChangesDTO> updateChanges(@Valid @RequestBody ChangesDTO changesDTO) throws URISyntaxException {
        log.debug("REST request to update Changes : {}", changesDTO);
        if (changesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChangesDTO result = changesService.save(changesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, changesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /changes : get all the changes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of changes in body
     */
    @GetMapping("/changes")
    @Timed
    public List<ChangesDTO> getAllChanges() {
        log.debug("REST request to get all Changes");
        return changesService.findAll();
    }

    /**
     * GET  /changes/:id : get the "id" changes.
     *
     * @param id the id of the changesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the changesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/changes/{id}")
    @Timed
    public ResponseEntity<ChangesDTO> getChanges(@PathVariable Long id) {
        log.debug("REST request to get Changes : {}", id);
        Optional<ChangesDTO> changesDTO = changesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(changesDTO);
    }

    /**
     * DELETE  /changes/:id : delete the "id" changes.
     *
     * @param id the id of the changesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/changes/{id}")
    @Timed
    public ResponseEntity<Void> deleteChanges(@PathVariable Long id) {
        log.debug("REST request to delete Changes : {}", id);
        changesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/user/{id}")
    @Timed
    public List<ChangesDTO> getUserChanges(@PathVariable Long id) {
        log.debug("REST request to get Changes : {}", id);
        return changesService.findAllByUser(id);

    }
}
