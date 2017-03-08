package com.shareit.app.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Booking entity.
 */
public class BookingDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    @NotNull
    private Double costPerKilometer;

    private Long driverId;

    private Long ownerId;

    private Long startAddressId;

    private Long endAddressId;

    private Long bookingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }
    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }
    public Double getCostPerKilometer() {
        return costPerKilometer;
    }

    public void setCostPerKilometer(Double costPerKilometer) {
        this.costPerKilometer = costPerKilometer;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long appUserId) {
        this.driverId = appUserId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long appUserId) {
        this.ownerId = appUserId;
    }

    public Long getStartAddressId() {
        return startAddressId;
    }

    public void setStartAddressId(Long addressId) {
        this.startAddressId = addressId;
    }

    public Long getEndAddressId() {
        return endAddressId;
    }

    public void setEndAddressId(Long addressId) {
        this.endAddressId = addressId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingDTO bookingDTO = (BookingDTO) o;

        if ( ! Objects.equals(id, bookingDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
            "id=" + id +
            ", startDate='" + startDate + "'" +
            ", endDate='" + endDate + "'" +
            ", costPerKilometer='" + costPerKilometer + "'" +
            '}';
    }
}
