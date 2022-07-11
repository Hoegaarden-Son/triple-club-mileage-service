package com.myeongokson.springboot.web.dto.reviews;

import com.myeongokson.springboot.domain.reviews.Reviews;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ReviewsRequestDto {
    private String type;
    private String action;
    private String reviewId;
    private String content;
    private String attachedPhotoIds;
    private String userId;
    private String placeId;

    @Builder
    public ReviewsRequestDto(String type, String action, String reviewId, String content, String attachedPhotoIds, String userId, String placeId) {
        this.type = type;
        this.action = action;
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public Reviews toEntity() {
        return Reviews.builder()
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build();
    }
}
