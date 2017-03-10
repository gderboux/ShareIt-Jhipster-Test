package com.shareit.app.repository;

import com.shareit.app.domain.AppUser;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AppUser entity.
 */
@SuppressWarnings("unused")
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

}
