package com.shareit.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shareit.app.service.FrequencyService;
import com.shareit.app.web.rest.util.HeaderUtil;
import com.shareit.app.service.dto.FrequencyDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Frequency.
 */
@RestController
@RequestMapping("/api")
public class FrequencyResource {

    private final Logger log = LoggerFactory.getLogger(FrequencyResource.class);

    private static final String ENTITY_NAME = "frequency";
        
    private final FrequencyService frequencyService;

    public FrequencyResource(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    /**
     * POST  /frequencies : Create a new frequency.
     *
     * @param frequencyDTO the frequencyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new frequencyDTO, or with status 400 (Bad Request) if the frequency has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/frequencies")
    @Timed
    public ResponseEntity<FrequencyDTO> createFrequency(@Valid @RequestBody FrequencyDTO frequencyDTO) throws URISyntaxException {
        log.debug("REST request to save Frequency : {}", frequencyDTO);
        if (frequencyDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new frequency cannot already have an ID")).body(null);
        }
        FrequencyDTO result = frequencyService.save(frequencyDTO);
        return ResponseEntity.created(new URI("/api/frequencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /frequencies : Updates an existing frequency.
     *
     * @param frequencyDTO the frequencyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated frequencyDTO,
     * or with status 400 (Bad Request) if the frequencyDTO is not valid,
     * or with status 500 (Internal Server Error) if the frequencyDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/frequencies")
    @Timed
    public ResponseEntity<FrequencyDTO> updateFrequency(@Valid @RequestBody FrequencyDTO frequencyDTO) throws URISyntaxException {
        log.debug("REST request to update Frequency : {}", frequencyDTO);
        if (frequencyDTO.getId() == null) {
            return createFrequency(frequencyDTO);
        }
        FrequencyDTO result = frequencyService.save(frequencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, frequencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /frequencies : get all the frequencies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of frequencies in body
     */
    @GetMapping("/frequencies")
    @Timed
    public List<FrequencyDTO> getAllFrequencies() {
        log.debug("REST request to get all Frequencies");
        return frequencyService.findAll();
    }

    /**
     * GET  /frequencies/:id : get the "id" frequency.
     *
     * @param id the id of the frequencyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the frequencyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/frequencies/{id}")
    @Timed
    public ResponseEntity<FrequencyDTO> getFrequency(@PathVariable Long id) {
        log.debug("REST request to get Frequency : {}", id);
        FrequencyDTO frequencyDTO = frequencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(frequencyDTO));
    }

    /**
     * DELETE  /frequencies/:id : delete the "id" frequency.
     *
     * @param id the id of the frequencyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/frequencies/{id}")
    @Timed
    public ResponseEntity<Void> deleteFrequency(@PathVariable Long id) {
        log.debug("REST request to delete Frequency : {}", id);
        frequencyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
