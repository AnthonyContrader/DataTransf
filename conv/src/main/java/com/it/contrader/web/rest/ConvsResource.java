package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.ConvsService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.service.dto.ConvsDTO;
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
 * REST controller for managing Convs.
 */
@RestController
@RequestMapping("/api")
public class ConvsResource {

    private final Logger log = LoggerFactory.getLogger(ConvsResource.class);

    private static final String ENTITY_NAME = "convs";

    private final ConvsService convsService;

    public ConvsResource(ConvsService convsService) {
        this.convsService = convsService;
    }

    /**
     * POST  /convs : Create a new convs.
     *
     * @param convsDTO the convsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new convsDTO, or with status 400 (Bad Request) if the convs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/convs")
    @Timed
    public ResponseEntity<ConvsDTO> createConvs(@Valid @RequestBody ConvsDTO convsDTO) throws URISyntaxException {
        log.debug("REST request to save Convs : {}", convsDTO);
        if (convsDTO.getId() != null) {
            throw new BadRequestAlertException("A new convs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConvsDTO result = convsService.save(convsDTO);
        return ResponseEntity.created(new URI("/api/convs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /convs : Updates an existing convs.
     *
     * @param convsDTO the convsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated convsDTO,
     * or with status 400 (Bad Request) if the convsDTO is not valid,
     * or with status 500 (Internal Server Error) if the convsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/convs")
    @Timed
    public ResponseEntity<ConvsDTO> updateConvs(@Valid @RequestBody ConvsDTO convsDTO) throws URISyntaxException {
        log.debug("REST request to update Convs : {}", convsDTO);
        if (convsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConvsDTO result = convsService.save(convsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, convsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /convs : get all the convs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of convs in body
     */
    @GetMapping("/convs")
    @Timed
    public List<ConvsDTO> getAllConvs() {
        log.debug("REST request to get all Convs");
        return convsService.findAll();
    }

    /**
     * GET  /convs/:id : get the "id" convs.
     *
     * @param id the id of the convsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the convsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/convs/{id}")
    @Timed
    public ResponseEntity<ConvsDTO> getConvs(@PathVariable Long id) {
        log.debug("REST request to get Convs : {}", id);
        Optional<ConvsDTO> convsDTO = convsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(convsDTO);
    }

    /**
     * DELETE  /convs/:id : delete the "id" convs.
     *
     * @param id the id of the convsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/convs/{id}")
    @Timed
    public ResponseEntity<Void> deleteConvs(@PathVariable Long id) {
        log.debug("REST request to delete Convs : {}", id);
        convsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/usr/{usr}")
    @Timed
    public List<ConvsDTO> findAllByUsr(@PathVariable Long usr){
    log.debug("REST request to get all Convs By usr : {}", usr);
        return convsService.findAllByUsr(usr);
    }
}
