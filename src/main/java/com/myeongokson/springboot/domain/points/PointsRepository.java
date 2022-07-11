package com.myeongokson.springboot.domain.points;

import com.myeongokson.springboot.domain.events.Events;
import com.myeongokson.springboot.domain.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointsRepository extends JpaRepository<Points, Long> {
    @Query("SELECT p FROM Points p ORDER BY p.id DESC")
    List<Points> findAllDesc();

    @Query("SELECT p FROM Points p WHERE p.userId=:userId ORDER BY p.id DESC")
    List<Points> findAllByUserIdDesc(@Param("userId") String userId);

    @Query("SELECT p FROM Points p WHERE p.userId=:userId")
    Points findByUserId(@Param("userId") String userId);

    @Query("SELECT sum(p.point) FROM Points p WHERE p.userId=:userId")
    int findPointByUserId(@Param("userId") String userId);

    @Query("SELECT p FROM Points p WHERE p.reviewId=:reviewId")
    Points findByReviewId(@Param("reviewId") String reviewId);
}

