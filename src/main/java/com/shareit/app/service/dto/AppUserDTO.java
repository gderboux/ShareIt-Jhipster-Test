package com.shareit.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.shareit.app.domain.enumeration.Gender;

/**
 * A DTO for the AppUser entity.
 */
public class AppUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Gender gender;

    @NotNull
    private String phoneNumber;

    @Lob
    private byte[] avatar;
    private String avatarContentType;

    @Lob
    private byte[] licence;
    private String licenceContentType;

    private Boolean isAuthorizedDriver;

    private Long carId;

    private Long addressId;

    private Long jhiUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }
    public byte[] getLicence() {
        return licence;
    }

    public void setLicence(byte[] licence) {
        this.licence = licence;
    }

    public String getLicenceContentType() {
        return licenceContentType;
    }

    public void setLicenceContentType(String licenceContentType) {
        this.licenceContentType = licenceContentType;
    }
    public Boolean getIsAuthorizedDriver() {
        return isAuthorizedDriver;
    }

    public void setIsAuthorizedDriver(Boolean isAuthorizedDriver) {
        this.isAuthorizedDriver = isAuthorizedDriver;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getJhiUserId() {
        return jhiUserId;
    }

    public void setJhiUserId(Long userId) {
        this.jhiUserId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppUserDTO appUserDTO = (AppUserDTO) o;

        if ( ! Objects.equals(id, appUserDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AppUserDTO{" +
            "id=" + id +
            ", gender='" + gender + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            ", avatar='" + avatar + "'" +
            ", licence='" + licence + "'" +
            ", isAuthorizedDriver='" + isAuthorizedDriver + "'" +
            '}';
    }
}
