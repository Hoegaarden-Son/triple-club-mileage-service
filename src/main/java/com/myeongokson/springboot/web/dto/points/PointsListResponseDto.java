package com.myeongokson.springboot.web.dto.points;

import com.myeongokson.springboot.domain.events.Events;
import com.myeongokson.springboot.domain.points.Points;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PointsListResponseDto {
    private final Long id;
    private final String reviewId;
    private final String userId;
    private final String placeId;
    private final int point;

    public PointsListResponseDto(Points entity) {
        this.id = entity.getId();
        this.reviewId = entity.getReviewId();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
        this.point = entity.getPoint();
    }
}
