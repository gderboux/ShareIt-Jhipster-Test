package com.shareit.app.service;

import com.shareit.app.domain.Address;
import com.shareit.app.repository.AddressRepository;
import com.shareit.app.service.dto.AddressDTO;
import com.shareit.app.service.mapper.AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Address.
 */
@Service
@Transactional
public class AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);
    
    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    /**
     * Save a address.
     *
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.addressDTOToAddress(addressDTO);
        address = addressRepository.save(address);
        AddressDTO result = addressMapper.addressToAddressDTO(address);
        return result;
    }

    /**
     *  Get all the addresses.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        log.debug("Request to get all Addresses");
        List<AddressDTO> result = addressRepository.findAll().stream()
            .map(addressMapper::addressToAddressDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one address by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AddressDTO findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        Address address = addressRepository.findOne(id);
        AddressDTO addressDTO = addressMapper.addressToAddressDTO(address);
        return addressDTO;
    }

    /**
     *  Delete the  address by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
    }
}
