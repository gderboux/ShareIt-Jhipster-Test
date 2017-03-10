package com.shareit.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.shareit.app.domain.enumeration.Color;

/**
 * A DTO for the Car entity.
 */
public class CarDTO implements Serializable {

    private Long id;

    @NotNull
    private String vin;

    @NotNull
    private String description;

    @NotNull
    private Color color;

    @NotNull
    private Integer numberOfSeat;

    @Lob
    private byte[] carPicture;
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

    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Integer getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }
    public byte[] getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(byte[] carPicture) {
        this.carPicture = carPicture;
    }

    public String getCarPictureContentType() {
        return carPictureContentType;
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

        CarDTO carDTO = (CarDTO) o;

        if ( ! Objects.equals(id, carDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
            "id=" + id +
            ", vin='" + vin + "'" +
            ", description='" + description + "'" +
            ", color='" + color + "'" +
            ", numberOfSeat='" + numberOfSeat + "'" +
            ", carPicture='" + carPicture + "'" +
            '}';
    }
}
