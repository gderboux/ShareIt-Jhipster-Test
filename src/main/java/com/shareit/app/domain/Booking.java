package com.shareit.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @NotNull
    @Column(name = "cost_per_kilometer", nullable = false)
    private Double costPerKilometer;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private AppUser driver;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private AppUser owner;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address startAddress;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address endAddress;

    @ManyToOne
    private Booking booking;

    @OneToMany(mappedBy = "booking")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> passengerBookings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Booking startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Booking endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getCostPerKilometer() {
        return costPerKilometer;
    }

    public Booking costPerKilometer(Double costPerKilometer) {
        this.costPerKilometer = costPerKilometer;
        return this;
    }

    public void setCostPerKilometer(Double costPerKilometer) {
        this.costPerKilometer = costPerKilometer;
    }

    public AppUser getDriver() {
        return driver;
    }

    public Booking driver(AppUser appUser) {
        this.driver = appUser;
        return this;
    }

    public void setDriver(AppUser appUser) {
        this.driver = appUser;
    }

    public AppUser getOwner() {
        return owner;
    }

    public Booking owner(AppUser appUser) {
        this.owner = appUser;
        return this;
    }

    public void setOwner(AppUser appUser) {
        this.owner = appUser;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public Booking startAddress(Address address) {
        this.startAddress = address;
        return this;
    }

    public void setStartAddress(Address address) {
        this.startAddress = address;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public Booking endAddress(Address address) {
        this.endAddress = address;
        return this;
    }

    public void setEndAddress(Address address) {
        this.endAddress = address;
    }

    public Booking getBooking() {
        return booking;
    }

    public Booking booking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Set<Booking> getPassengerBookings() {
        return passengerBookings;
    }

    public Booking passengerBookings(Set<Booking> bookings) {
        this.passengerBookings = bookings;
        return this;
    }

    public Booking addPassengerBookings(Booking booking) {
        this.passengerBookings.add(booking);
        booking.setBooking(this);
        return this;
    }

    public Booking removePassengerBookings(Booking booking) {
        this.passengerBookings.remove(booking);
        booking.setBooking(null);
        return this;
    }

    public void setPassengerBookings(Set<Booking> bookings) {
        this.passengerBookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        if (booking.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + id +
            ", startDate='" + startDate + "'" +
            ", endDate='" + endDate + "'" +
            ", costPerKilometer='" + costPerKilometer + "'" +
            '}';
    }
}
