package com.shareit.app.service;

import com.shareit.app.domain.Frequency;
import com.shareit.app.repository.FrequencyRepository;
import com.shareit.app.service.dto.FrequencyDTO;
import com.shareit.app.service.mapper.FrequencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Frequency.
 */
@Service
@Transactional
public class FrequencyService {

    private final Logger log = LoggerFactory.getLogger(FrequencyService.class);
    
    private final FrequencyRepository frequencyRepository;

    private final FrequencyMapper frequencyMapper;

    public FrequencyService(FrequencyRepository frequencyRepository, FrequencyMapper frequencyMapper) {
        this.frequencyRepository = frequencyRepository;
        this.frequencyMapper = frequencyMapper;
    }

    /**
     * Save a frequency.
     *
     * @param frequencyDTO the entity to save
     * @return the persisted entity
     */
    public FrequencyDTO save(FrequencyDTO frequencyDTO) {
        log.debug("Request to save Frequency : {}", frequencyDTO);
        Frequency frequency = frequencyMapper.frequencyDTOToFrequency(frequencyDTO);
        frequency = frequencyRepository.save(frequency);
        FrequencyDTO result = frequencyMapper.frequencyToFrequencyDTO(frequency);
        return result;
    }

    /**
     *  Get all the frequencies.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FrequencyDTO> findAll() {
        log.debug("Request to get all Frequencies");
        List<FrequencyDTO> result = frequencyRepository.findAll().stream()
            .map(frequencyMapper::frequencyToFrequencyDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one frequency by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public FrequencyDTO findOne(Long id) {
        log.debug("Request to get Frequency : {}", id);
        Frequency frequency = frequencyRepository.findOne(id);
        FrequencyDTO frequencyDTO = frequencyMapper.frequencyToFrequencyDTO(frequency);
        return frequencyDTO;
    }

    /**
     *  Delete the  frequency by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Frequency : {}", id);
        frequencyRepository.delete(id);
    }
}
