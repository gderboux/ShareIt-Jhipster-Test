package com.shareit.app.service;

import com.shareit.app.domain.AppUser;
import com.shareit.app.repository.AppUserRepository;
import com.shareit.app.service.dto.AppUserDTO;
import com.shareit.app.service.mapper.AppUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AppUser.
 */
@Service
@Transactional
public class AppUserService {

    private final Logger log = LoggerFactory.getLogger(AppUserService.class);
    
    private final AppUserRepository appUserRepository;

    private final AppUserMapper appUserMapper;

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    /**
     * Save a appUser.
     *
     * @param appUserDTO the entity to save
     * @return the persisted entity
     */
    public AppUserDTO save(AppUserDTO appUserDTO) {
        log.debug("Request to save AppUser : {}", appUserDTO);
        AppUser appUser = appUserMapper.appUserDTOToAppUser(appUserDTO);
        appUser = appUserRepository.save(appUser);
        AppUserDTO result = appUserMapper.appUserToAppUserDTO(appUser);
        return result;
    }

    /**
     *  Get all the appUsers.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AppUserDTO> findAll() {
        log.debug("Request to get all AppUsers");
        List<AppUserDTO> result = appUserRepository.findAll().stream()
            .map(appUserMapper::appUserToAppUserDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one appUser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AppUserDTO findOne(Long id) {
        log.debug("Request to get AppUser : {}", id);
        AppUser appUser = appUserRepository.findOne(id);
        AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO(appUser);
        return appUserDTO;
    }

    /**
     *  Delete the  appUser by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AppUser : {}", id);
        appUserRepository.delete(id);
    }
}
