package com.shareit.app.repository;

import com.shareit.app.domain.Feedback;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Feedback entity.
 */
@SuppressWarnings("unused")
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
