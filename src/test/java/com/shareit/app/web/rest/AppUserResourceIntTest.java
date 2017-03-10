package com.shareit.app.web.rest;

import com.shareit.app.ShareItV2App;

import com.shareit.app.domain.AppUser;
import com.shareit.app.domain.Address;
import com.shareit.app.domain.User;
import com.shareit.app.repository.AppUserRepository;
import com.shareit.app.service.AppUserService;
import com.shareit.app.service.dto.AppUserDTO;
import com.shareit.app.service.mapper.AppUserMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.shareit.app.domain.enumeration.Gender;
/**
 * Test class for the AppUserResource REST controller.
 *
 * @see AppUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareItV2App.class)
public class AppUserResourceIntTest {

    private static final Gender DEFAULT_GENDER = Gender.HOMME;
    private static final Gender UPDATED_GENDER = Gender.FEMME;

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final byte[] DEFAULT_AVATAR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AVATAR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_AVATAR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AVATAR_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_LICENCE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LICENCE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_LICENCE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LICENCE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_IS_AUTHORIZED_DRIVER = false;
    private static final Boolean UPDATED_IS_AUTHORIZED_DRIVER = true;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppUserMockMvc;

    private AppUser appUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AppUserResource appUserResource = new AppUserResource(appUserService);
        this.restAppUserMockMvc = MockMvcBuilders.standaloneSetup(appUserResource)
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
    public static AppUser createEntity(EntityManager em) {
        AppUser appUser = new AppUser()
                .gender(DEFAULT_GENDER)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .avatar(DEFAULT_AVATAR)
                .avatarContentType(DEFAULT_AVATAR_CONTENT_TYPE)
                .licence(DEFAULT_LICENCE)
                .licenceContentType(DEFAULT_LICENCE_CONTENT_TYPE)
                .isAuthorizedDriver(DEFAULT_IS_AUTHORIZED_DRIVER);
        // Add required entity
        Address address = AddressResourceIntTest.createEntity(em);
        em.persist(address);
        em.flush();
        appUser.setAddress(address);
        // Add required entity
        User jhiUser = UserResourceIntTest.createEntity(em);
        em.persist(jhiUser);
        em.flush();
        appUser.setJhiUser(jhiUser);
        return appUser;
    }

    @Before
    public void initTest() {
        appUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppUser() throws Exception {
        int databaseSizeBeforeCreate = appUserRepository.findAll().size();

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(appUser);

        restAppUserMockMvc.perform(post("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isCreated());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate + 1);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAppUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testAppUser.getAvatarContentType()).isEqualTo(DEFAULT_AVATAR_CONTENT_TYPE);
        assertThat(testAppUser.getLicence()).isEqualTo(DEFAULT_LICENCE);
        assertThat(testAppUser.getLicenceContentType()).isEqualTo(DEFAULT_LICENCE_CONTENT_TYPE);
        assertThat(testAppUser.isIsAuthorizedDriver()).isEqualTo(DEFAULT_IS_AUTHORIZED_DRIVER);
    }

    @Test
    @Transactional
    public void createAppUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appUserRepository.findAll().size();

        // Create the AppUser with an existing ID
        AppUser existingAppUser = new AppUser();
        existingAppUser.setId(1L);
        AppUserDTO existingAppUserDTO = appUserMapper.appUserToAppUserDTO(existingAppUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppUserMockMvc.perform(post("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAppUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setGender(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(appUser);

        restAppUserMockMvc.perform(post("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setPhoneNumber(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(appUser);

        restAppUserMockMvc.perform(post("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppUsers() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        // Get all the appUserList
        restAppUserMockMvc.perform(get("/api/app-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].avatarContentType").value(hasItem(DEFAULT_AVATAR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(Base64Utils.encodeToString(DEFAULT_AVATAR))))
            .andExpect(jsonPath("$.[*].licenceContentType").value(hasItem(DEFAULT_LICENCE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].licence").value(hasItem(Base64Utils.encodeToString(DEFAULT_LICENCE))))
            .andExpect(jsonPath("$.[*].isAuthorizedDriver").value(hasItem(DEFAULT_IS_AUTHORIZED_DRIVER.booleanValue())));
    }

    @Test
    @Transactional
    public void getAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        // Get the appUser
        restAppUserMockMvc.perform(get("/api/app-users/{id}", appUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appUser.getId().intValue()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.avatarContentType").value(DEFAULT_AVATAR_CONTENT_TYPE))
            .andExpect(jsonPath("$.avatar").value(Base64Utils.encodeToString(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.licenceContentType").value(DEFAULT_LICENCE_CONTENT_TYPE))
            .andExpect(jsonPath("$.licence").value(Base64Utils.encodeToString(DEFAULT_LICENCE)))
            .andExpect(jsonPath("$.isAuthorizedDriver").value(DEFAULT_IS_AUTHORIZED_DRIVER.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAppUser() throws Exception {
        // Get the appUser
        restAppUserMockMvc.perform(get("/api/app-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();

        // Update the appUser
        AppUser updatedAppUser = appUserRepository.findOne(appUser.getId());
        updatedAppUser
                .gender(UPDATED_GENDER)
                .phoneNumber(UPDATED_PHONE_NUMBER)
                .avatar(UPDATED_AVATAR)
                .avatarContentType(UPDATED_AVATAR_CONTENT_TYPE)
                .licence(UPDATED_LICENCE)
                .licenceContentType(UPDATED_LICENCE_CONTENT_TYPE)
                .isAuthorizedDriver(UPDATED_IS_AUTHORIZED_DRIVER);
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(updatedAppUser);

        restAppUserMockMvc.perform(put("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isOk());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAppUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testAppUser.getAvatarContentType()).isEqualTo(UPDATED_AVATAR_CONTENT_TYPE);
        assertThat(testAppUser.getLicence()).isEqualTo(UPDATED_LICENCE);
        assertThat(testAppUser.getLicenceContentType()).isEqualTo(UPDATED_LICENCE_CONTENT_TYPE);
        assertThat(testAppUser.isIsAuthorizedDriver()).isEqualTo(UPDATED_IS_AUTHORIZED_DRIVER);
    }

    @Test
    @Transactional
    public void updateNonExistingAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(appUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppUserMockMvc.perform(put("/api/app-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isCreated());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);
        int databaseSizeBeforeDelete = appUserRepository.findAll().size();

        // Get the appUser
        restAppUserMockMvc.perform(delete("/api/app-users/{id}", appUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppUser.class);
    }
}
