package com.shareit.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.shareit.app.domain.enumeration.Gender;

/**
 * A AppUser.
 */
@Entity
@Table(name = "app_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "avatar_content_type")
    private String avatarContentType;

    @Lob
    @Column(name = "licence")
    private byte[] licence;

    @Column(name = "licence_content_type")
    private String licenceContentType;

    @Column(name = "is_authorized_driver")
    private Boolean isAuthorizedDriver;

    @OneToOne
    @JoinColumn(unique = true)
    private Car car;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address address;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User jhiUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public AppUser gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AppUser phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public AppUser avatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public AppUser avatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
        return this;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }

    public byte[] getLicence() {
        return licence;
    }

    public AppUser licence(byte[] licence) {
        this.licence = licence;
        return this;
    }

    public void setLicence(byte[] licence) {
        this.licence = licence;
    }

    public String getLicenceContentType() {
        return licenceContentType;
    }

    public AppUser licenceContentType(String licenceContentType) {
        this.licenceContentType = licenceContentType;
        return this;
    }

    public void setLicenceContentType(String licenceContentType) {
        this.licenceContentType = licenceContentType;
    }

    public Boolean isIsAuthorizedDriver() {
        return isAuthorizedDriver;
    }

    public AppUser isAuthorizedDriver(Boolean isAuthorizedDriver) {
        this.isAuthorizedDriver = isAuthorizedDriver;
        return this;
    }

    public void setIsAuthorizedDriver(Boolean isAuthorizedDriver) {
        this.isAuthorizedDriver = isAuthorizedDriver;
    }

    public Car getCar() {
        return car;
    }

    public AppUser car(Car car) {
        this.car = car;
        return this;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Address getAddress() {
        return address;
    }

    public AppUser address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getJhiUser() {
        return jhiUser;
    }

    public AppUser jhiUser(User user) {
        this.jhiUser = user;
        return this;
    }

    public void setJhiUser(User user) {
        this.jhiUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppUser appUser = (AppUser) o;
        if (appUser.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, appUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AppUser{" +
            "id=" + id +
            ", gender='" + gender + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            ", avatar='" + avatar + "'" +
            ", avatarContentType='" + avatarContentType + "'" +
            ", licence='" + licence + "'" +
            ", licenceContentType='" + licenceContentType + "'" +
            ", isAuthorizedDriver='" + isAuthorizedDriver + "'" +
            '}';
    }
}
