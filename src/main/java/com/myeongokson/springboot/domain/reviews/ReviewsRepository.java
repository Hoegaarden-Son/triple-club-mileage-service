package com.myeongokson.springboot.domain.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT p FROM Reviews p ORDER BY p.id DESC")
    List<Reviews> findAllDesc();

    @Query("SELECT p FROM Reviews p WHERE p.reviewId=:reviewId")
    Reviews findByReviewId(@Param("reviewId") String reviewId);

    // limit 1 (why not?)
    @Query("SELECT count(*) FROM Reviews p WHERE p.placeId=:placeId ")
    int findByPlaceId(@Param("placeId") String placeId);
}
