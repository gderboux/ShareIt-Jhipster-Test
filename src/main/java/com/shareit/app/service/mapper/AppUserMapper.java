package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.AppUserDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity AppUser and its DTO AppUserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface AppUserMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "jhiUser.id", target = "jhiUserId")
    AppUserDTO appUserToAppUserDTO(AppUser appUser);

    List<AppUserDTO> appUsersToAppUserDTOs(List<AppUser> appUsers);

    @Mapping(source = "carId", target = "car")
    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "jhiUserId", target = "jhiUser")
    AppUser appUserDTOToAppUser(AppUserDTO appUserDTO);

    List<AppUser> appUserDTOsToAppUsers(List<AppUserDTO> appUserDTOs);

    default Car carFromId(Long id) {
        if (id == null) {
            return null;
        }
        Car car = new Car();
        car.setId(id);
        return car;
    }

    default Address addressFromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
