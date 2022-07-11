package com.myeongokson.springboot.web.dto.reviews;

import com.myeongokson.springboot.domain.reviews.Reviews;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewsListResponseDto {
    private final Long id;
    private final String reviewId;
    private final String userId;
    private final String placeId;
    private final String content;
    private final LocalDateTime modifiedDate;

    public ReviewsListResponseDto(Reviews entity) {
        this.id = entity.getId();
        this.reviewId = entity.getReviewId();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }

}
