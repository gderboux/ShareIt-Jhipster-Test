package com.shareit.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Frequency.
 */
@Entity
@Table(name = "frequency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Frequency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "monday")
    private Boolean monday;

    @Column(name = "tuesday")
    private Boolean tuesday;

    @Column(name = "wednesday")
    private Boolean wednesday;

    @Column(name = "thursday")
    private Boolean thursday;

    @Column(name = "friday")
    private Boolean friday;

    @Column(name = "saturday")
    private Boolean saturday;

    @Column(name = "sunday")
    private Boolean sunday;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isMonday() {
        return monday;
    }

    public Frequency monday(Boolean monday) {
        this.monday = monday;
        return this;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean isTuesday() {
        return tuesday;
    }

    public Frequency tuesday(Boolean tuesday) {
        this.tuesday = tuesday;
        return this;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean isWednesday() {
        return wednesday;
    }

    public Frequency wednesday(Boolean wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean isThursday() {
        return thursday;
    }

    public Frequency thursday(Boolean thursday) {
        this.thursday = thursday;
        return this;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean isFriday() {
        return friday;
    }

    public Frequency friday(Boolean friday) {
        this.friday = friday;
        return this;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean isSaturday() {
        return saturday;
    }

    public Frequency saturday(Boolean saturday) {
        this.saturday = saturday;
        return this;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean isSunday() {
        return sunday;
    }

    public Frequency sunday(Boolean sunday) {
        this.sunday = sunday;
        return this;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    public Booking getBooking() {
        return booking;
    }

    public Frequency booking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frequency frequency = (Frequency) o;
        if (frequency.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, frequency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Frequency{" +
            "id=" + id +
            ", monday='" + monday + "'" +
            ", tuesday='" + tuesday + "'" +
            ", wednesday='" + wednesday + "'" +
            ", thursday='" + thursday + "'" +
            ", friday='" + friday + "'" +
            ", saturday='" + saturday + "'" +
            ", sunday='" + sunday + "'" +
            '}';
    }
}
