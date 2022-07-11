package com.myeongokson.springboot.web.dto.points;

import com.myeongokson.springboot.domain.events.Events;
import com.myeongokson.springboot.domain.points.Points;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PointsSaveRequestDto {
    private String userId;
    private String reviewId;
    private String placeId;
    private int point;

    @Builder
    public PointsSaveRequestDto(String userId, String reviewId, String placeId, int point) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.point = point;
    }

    public Points toEntity() {
        return Points.builder()
                .reviewId(reviewId)
                .userId(userId)
                .placeId(placeId)
                .point(point)
                .build();
    }
}
