package com.shareit.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.shareit.app.domain.enumeration.Color;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "vin", nullable = false)
    private String vin;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @NotNull
    @Column(name = "number_of_seat", nullable = false)
    private Integer numberOfSeat;

    @Lob
    @Column(name = "car_picture")
    private byte[] carPicture;

    @Column(name = "car_picture_content_type")
    private String carPictureContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public Car vin(String vin) {
        this.vin = vin;
        return this;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public Car description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getColor() {
        return color;
    }

    public Car color(Color color) {
        this.color = color;
        return this;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getNumberOfSeat() {
        return numberOfSeat;
    }

    public Car numberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
        return this;
    }

    public void setNumberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public byte[] getCarPicture() {
        return carPicture;
    }

    public Car carPicture(byte[] carPicture) {
        this.carPicture = carPicture;
        return this;
    }

    public void setCarPicture(byte[] carPicture) {
        this.carPicture = carPicture;
    }

    public String getCarPictureContentType() {
        return carPictureContentType;
    }

    public Car carPictureContentType(String carPictureContentType) {
        this.carPictureContentType = carPictureContentType;
        return this;
    }

    public void setCarPictureContentType(String carPictureContentType) {
        this.carPictureContentType = carPictureContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        if (car.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", vin='" + vin + "'" +
            ", description='" + description + "'" +
            ", color='" + color + "'" +
            ", numberOfSeat='" + numberOfSeat + "'" +
            ", carPicture='" + carPicture + "'" +
            ", carPictureContentType='" + carPictureContentType + "'" +
            '}';
    }
}
