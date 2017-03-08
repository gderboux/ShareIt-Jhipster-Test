package com.shareit.app.service;

import com.shareit.app.domain.Feedback;
import com.shareit.app.repository.FeedbackRepository;
import com.shareit.app.service.dto.FeedbackDTO;
import com.shareit.app.service.mapper.FeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Feedback.
 */
@Service
@Transactional
public class FeedbackService {

    private final Logger log = LoggerFactory.getLogger(FeedbackService.class);
    
    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * Save a feedback.
     *
     * @param feedbackDTO the entity to save
     * @return the persisted entity
     */
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        log.debug("Request to save Feedback : {}", feedbackDTO);
        Feedback feedback = feedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        feedback = feedbackRepository.save(feedback);
        FeedbackDTO result = feedbackMapper.feedbackToFeedbackDTO(feedback);
        return result;
    }

    /**
     *  Get all the feedbacks.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FeedbackDTO> findAll() {
        log.debug("Request to get all Feedbacks");
        List<FeedbackDTO> result = feedbackRepository.findAll().stream()
            .map(feedbackMapper::feedbackToFeedbackDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one feedback by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public FeedbackDTO findOne(Long id) {
        log.debug("Request to get Feedback : {}", id);
        Feedback feedback = feedbackRepository.findOne(id);
        FeedbackDTO feedbackDTO = feedbackMapper.feedbackToFeedbackDTO(feedback);
        return feedbackDTO;
    }

    /**
     *  Delete the  feedback by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Feedback : {}", id);
        feedbackRepository.delete(id);
    }
}
