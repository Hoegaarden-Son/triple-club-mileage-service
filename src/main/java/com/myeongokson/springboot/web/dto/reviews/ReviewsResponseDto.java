package com.myeongokson.springboot.web.dto.reviews;

import com.myeongokson.springboot.domain.reviews.Reviews;
import lombok.Getter;

@Getter
public class ReviewsResponseDto {
    private Long id;
    private String content;
    private String reviewId;
    private String attachedPhotoIds;
    private String userId;
    private String placeId;

    public ReviewsResponseDto(Reviews entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.reviewId = entity.getReviewId();
        this.attachedPhotoIds = entity.getAttachedPhotoIds();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
    }
}
