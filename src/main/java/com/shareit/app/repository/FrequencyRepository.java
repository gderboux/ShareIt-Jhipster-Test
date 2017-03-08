package com.shareit.app.repository;

import com.shareit.app.domain.Frequency;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Frequency entity.
 */
@SuppressWarnings("unused")
public interface FrequencyRepository extends JpaRepository<Frequency,Long> {

}
