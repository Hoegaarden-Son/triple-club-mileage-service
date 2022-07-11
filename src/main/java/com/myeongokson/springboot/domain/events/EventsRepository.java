package com.myeongokson.springboot.domain.events;

import com.myeongokson.springboot.domain.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Long> {

    @Query("SELECT p FROM Events p ORDER BY p.id DESC")
    List<Events> findAllDesc();

    @Query("SELECT p FROM Events p WHERE p.reviewId=:reviewId")
    Events findByReviewId(@Param("reviewId") String reviewId);
}
