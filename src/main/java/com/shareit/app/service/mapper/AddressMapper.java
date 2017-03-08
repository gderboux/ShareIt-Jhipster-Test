package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.AddressDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressMapper {

    AddressDTO addressToAddressDTO(Address address);

    List<AddressDTO> addressesToAddressDTOs(List<Address> addresses);

    Address addressDTOToAddress(AddressDTO addressDTO);

    List<Address> addressDTOsToAddresses(List<AddressDTO> addressDTOs);
}
