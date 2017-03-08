package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.FrequencyDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Frequency and its DTO FrequencyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FrequencyMapper {

    @Mapping(source = "booking.id", target = "bookingId")
    FrequencyDTO frequencyToFrequencyDTO(Frequency frequency);

    List<FrequencyDTO> frequenciesToFrequencyDTOs(List<Frequency> frequencies);

    @Mapping(source = "bookingId", target = "booking")
    Frequency frequencyDTOToFrequency(FrequencyDTO frequencyDTO);

    List<Frequency> frequencyDTOsToFrequencies(List<FrequencyDTO> frequencyDTOs);

    default Booking bookingFromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }
}
