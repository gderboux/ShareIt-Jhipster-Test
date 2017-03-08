package com.shareit.app.web.rest;

import com.shareit.app.ShareItV2App;

import com.shareit.app.domain.Frequency;
import com.shareit.app.domain.Booking;
import com.shareit.app.repository.FrequencyRepository;
import com.shareit.app.service.FrequencyService;
import com.shareit.app.service.dto.FrequencyDTO;
import com.shareit.app.service.mapper.FrequencyMapper;
import com.shareit.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FrequencyResource REST controller.
 *
 * @see FrequencyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareItV2App.class)
public class FrequencyResourceIntTest {

    private static final Boolean DEFAULT_MONDAY = false;
    private static final Boolean UPDATED_MONDAY = true;

    private static final Boolean DEFAULT_TUESDAY = false;
    private static final Boolean UPDATED_TUESDAY = true;

    private static final Boolean DEFAULT_WEDNESDAY = false;
    private static final Boolean UPDATED_WEDNESDAY = true;

    private static final Boolean DEFAULT_THURSDAY = false;
    private static final Boolean UPDATED_THURSDAY = true;

    private static final Boolean DEFAULT_FRIDAY = false;
    private static final Boolean UPDATED_FRIDAY = true;

    private static final Boolean DEFAULT_SATURDAY = false;
    private static final Boolean UPDATED_SATURDAY = true;

    private static final Boolean DEFAULT_SUNDAY = false;
    private static final Boolean UPDATED_SUNDAY = true;

    @Autowired
    private FrequencyRepository frequencyRepository;

    @Autowired
    private FrequencyMapper frequencyMapper;

    @Autowired
    private FrequencyService frequencyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFrequencyMockMvc;

    private Frequency frequency;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FrequencyResource frequencyResource = new FrequencyResource(frequencyService);
        this.restFrequencyMockMvc = MockMvcBuilders.standaloneSetup(frequencyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frequency createEntity(EntityManager em) {
        Frequency frequency = new Frequency()
                .monday(DEFAULT_MONDAY)
                .tuesday(DEFAULT_TUESDAY)
                .wednesday(DEFAULT_WEDNESDAY)
                .thursday(DEFAULT_THURSDAY)
                .friday(DEFAULT_FRIDAY)
                .saturday(DEFAULT_SATURDAY)
                .sunday(DEFAULT_SUNDAY);
        // Add required entity
        Booking booking = BookingResourceIntTest.createEntity(em);
        em.persist(booking);
        em.flush();
        frequency.setBooking(booking);
        return frequency;
    }

    @Before
    public void initTest() {
        frequency = createEntity(em);
    }

    @Test
    @Transactional
    public void createFrequency() throws Exception {
        int databaseSizeBeforeCreate = frequencyRepository.findAll().size();

        // Create the Frequency
        FrequencyDTO frequencyDTO = frequencyMapper.frequencyToFrequencyDTO(frequency);

        restFrequencyMockMvc.perform(post("/api/frequencies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frequencyDTO)))
            .andExpect(status().isCreated());

        // Validate the Frequency in the database
        List<Frequency> frequencyList = frequencyRepository.findAll();
        assertThat(frequencyList).hasSize(databaseSizeBeforeCreate + 1);
        Frequency testFrequency = frequencyList.get(frequencyList.size() - 1);
        assertThat(testFrequency.isMonday()).isEqualTo(DEFAULT_MONDAY);
        assertThat(testFrequency.isTuesday()).isEqualTo(DEFAULT_TUESDAY);
        assertThat(testFrequency.isWednesday()).isEqualTo(DEFAULT_WEDNESDAY);
        assertThat(testFrequency.isThursday()).isEqualTo(DEFAULT_THURSDAY);
        assertThat(testFrequency.isFriday()).isEqualTo(DEFAULT_FRIDAY);
        assertThat(testFrequency.isSaturday()).isEqualTo(DEFAULT_SATURDAY);
        assertThat(testFrequency.isSunday()).isEqualTo(DEFAULT_SUNDAY);
    }

    @Test
    @Transactional
    public void createFrequencyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = frequencyRepository.findAll().size();

        // Create the Frequency with an existing ID
        Frequency existingFrequency = new Frequency();
        existingFrequency.setId(1L);
        FrequencyDTO existingFrequencyDTO = frequencyMapper.frequencyToFrequencyDTO(existingFrequency);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrequencyMockMvc.perform(post("/api/frequencies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFrequencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Frequency> frequencyList = frequencyRepository.findAll();
        assertThat(frequencyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFrequencies() throws Exception {
        // Initialize the database
        frequencyRepository.saveAndFlush(frequency);

        // Get all the frequencyList
        restFrequencyMockMvc.perform(get("/api/frequencies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frequency.getId().intValue())))
            .andExpect(jsonPath("$.[*].monday").value(hasItem(DEFAULT_MONDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].tuesday").value(hasItem(DEFAULT_TUESDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].wednesday").value(hasItem(DEFAULT_WEDNESDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].thursday").value(hasItem(DEFAULT_THURSDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].friday").value(hasItem(DEFAULT_FRIDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].saturday").value(hasItem(DEFAULT_SATURDAY.booleanValue())))
            .andExpect(jsonPath("$.[*].sunday").value(hasItem(DEFAULT_SUNDAY.booleanValue())));
    }

    @Test
    @Transactional
    public void getFrequency() throws Exception {
        // Initialize the database
        frequencyRepository.saveAndFlush(frequency);

        // Get the frequency
        restFrequencyMockMvc.perform(get("/api/frequencies/{id}", frequency.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(frequency.getId().intValue()))
            .andExpect(jsonPath("$.monday").value(DEFAULT_MONDAY.booleanValue()))
            .andExpect(jsonPath("$.tuesday").value(DEFAULT_TUESDAY.booleanValue()))
            .andExpect(jsonPath("$.wednesday").value(DEFAULT_WEDNESDAY.booleanValue()))
            .andExpect(jsonPath("$.thursday").value(DEFAULT_THURSDAY.booleanValue()))
            .andExpect(jsonPath("$.friday").value(DEFAULT_FRIDAY.booleanValue()))
            .andExpect(jsonPath("$.saturday").value(DEFAULT_SATURDAY.booleanValue()))
            .andExpect(jsonPath("$.sunday").value(DEFAULT_SUNDAY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFrequency() throws Exception {
        // Get the frequency
        restFrequencyMockMvc.perform(get("/api/frequencies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFrequency() throws Exception {
        // Initialize the database
        frequencyRepository.saveAndFlush(frequency);
        int databaseSizeBeforeUpdate = frequencyRepository.findAll().size();

        // Update the frequency
        Frequency updatedFrequency = frequencyRepository.findOne(frequency.getId());
        updatedFrequency
                .monday(UPDATED_MONDAY)
                .tuesday(UPDATED_TUESDAY)
                .wednesday(UPDATED_WEDNESDAY)
                .thursday(UPDATED_THURSDAY)
                .friday(UPDATED_FRIDAY)
                .saturday(UPDATED_SATURDAY)
                .sunday(UPDATED_SUNDAY);
        FrequencyDTO frequencyDTO = frequencyMapper.frequencyToFrequencyDTO(updatedFrequency);

        restFrequencyMockMvc.perform(put("/api/frequencies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frequencyDTO)))
            .andExpect(status().isOk());

        // Validate the Frequency in the database
        List<Frequency> frequencyList = frequencyRepository.findAll();
        assertThat(frequencyList).hasSize(databaseSizeBeforeUpdate);
        Frequency testFrequency = frequencyList.get(frequencyList.size() - 1);
        assertThat(testFrequency.isMonday()).isEqualTo(UPDATED_MONDAY);
        assertThat(testFrequency.isTuesday()).isEqualTo(UPDATED_TUESDAY);
        assertThat(testFrequency.isWednesday()).isEqualTo(UPDATED_WEDNESDAY);
        assertThat(testFrequency.isThursday()).isEqualTo(UPDATED_THURSDAY);
        assertThat(testFrequency.isFriday()).isEqualTo(UPDATED_FRIDAY);
        assertThat(testFrequency.isSaturday()).isEqualTo(UPDATED_SATURDAY);
        assertThat(testFrequency.isSunday()).isEqualTo(UPDATED_SUNDAY);
    }

    @Test
    @Transactional
    public void updateNonExistingFrequency() throws Exception {
        int databaseSizeBeforeUpdate = frequencyRepository.findAll().size();

        // Create the Frequency
        FrequencyDTO frequencyDTO = frequencyMapper.frequencyToFrequencyDTO(frequency);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFrequencyMockMvc.perform(put("/api/frequencies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frequencyDTO)))
            .andExpect(status().isCreated());

        // Validate the Frequency in the database
        List<Frequency> frequencyList = frequencyRepository.findAll();
        assertThat(frequencyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFrequency() throws Exception {
        // Initialize the database
        frequencyRepository.saveAndFlush(frequency);
        int databaseSizeBeforeDelete = frequencyRepository.findAll().size();

        // Get the frequency
        restFrequencyMockMvc.perform(delete("/api/frequencies/{id}", frequency.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Frequency> frequencyList = frequencyRepository.findAll();
        assertThat(frequencyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frequency.class);
    }
}
