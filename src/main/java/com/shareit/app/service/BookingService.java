package com.shareit.app.service;

import com.shareit.app.domain.Booking;
import com.shareit.app.repository.BookingRepository;
import com.shareit.app.service.dto.BookingDTO;
import com.shareit.app.service.mapper.BookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Booking.
 */
@Service
@Transactional
public class BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);
    
    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    /**
     * Save a booking.
     *
     * @param bookingDTO the entity to save
     * @return the persisted entity
     */
    public BookingDTO save(BookingDTO bookingDTO) {
        log.debug("Request to save Booking : {}", bookingDTO);
        Booking booking = bookingMapper.bookingDTOToBooking(bookingDTO);
        booking = bookingRepository.save(booking);
        BookingDTO result = bookingMapper.bookingToBookingDTO(booking);
        return result;
    }

    /**
     *  Get all the bookings.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<BookingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookings");
        Page<Booking> result = bookingRepository.findAll(pageable);
        return result.map(booking -> bookingMapper.bookingToBookingDTO(booking));
    }

    /**
     *  Get one booking by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public BookingDTO findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        Booking booking = bookingRepository.findOne(id);
        BookingDTO bookingDTO = bookingMapper.bookingToBookingDTO(booking);
        return bookingDTO;
    }

    /**
     *  Delete the  booking by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Booking : {}", id);
        bookingRepository.delete(id);
    }
}
