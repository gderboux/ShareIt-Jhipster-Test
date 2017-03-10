package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.BookingDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Booking and its DTO BookingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookingMapper {

    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "startAddress.id", target = "startAddressId")
    @Mapping(source = "endAddress.id", target = "endAddressId")
    @Mapping(source = "booking.id", target = "bookingId")
    BookingDTO bookingToBookingDTO(Booking booking);

    List<BookingDTO> bookingsToBookingDTOs(List<Booking> bookings);

    @Mapping(source = "driverId", target = "driver")
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "startAddressId", target = "startAddress")
    @Mapping(source = "endAddressId", target = "endAddress")
    @Mapping(source = "bookingId", target = "booking")
    @Mapping(target = "passengerBookings", ignore = true)
    Booking bookingDTOToBooking(BookingDTO bookingDTO);

    List<Booking> bookingDTOsToBookings(List<BookingDTO> bookingDTOs);

    default AppUser appUserFromId(Long id) {
        if (id == null) {
            return null;
        }
        AppUser appUser = new AppUser();
        appUser.setId(id);
        return appUser;
    }

    default Address addressFromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }

    default Booking bookingFromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }
}
