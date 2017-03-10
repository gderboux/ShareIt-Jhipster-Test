package com.shareit.app.service.mapper;

import com.shareit.app.domain.*;
import com.shareit.app.service.dto.FeedbackDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Feedback and its DTO FeedbackDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeedbackMapper {

    @Mapping(source = "booking.id", target = "bookingId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "reporter.id", target = "reporterId")
    FeedbackDTO feedbackToFeedbackDTO(Feedback feedback);

    List<FeedbackDTO> feedbacksToFeedbackDTOs(List<Feedback> feedbacks);

    @Mapping(source = "bookingId", target = "booking")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "reporterId", target = "reporter")
    Feedback feedbackDTOToFeedback(FeedbackDTO feedbackDTO);

    List<Feedback> feedbackDTOsToFeedbacks(List<FeedbackDTO> feedbackDTOs);

    default Booking bookingFromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }

    default AppUser appUserFromId(Long id) {
        if (id == null) {
            return null;
        }
        AppUser appUser = new AppUser();
        appUser.setId(id);
        return appUser;
    }
}
