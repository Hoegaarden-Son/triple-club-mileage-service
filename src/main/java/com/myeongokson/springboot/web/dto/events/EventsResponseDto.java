package com.myeongokson.springboot.web.dto.events;

import com.myeongokson.springboot.domain.events.Events;
import lombok.Getter;

@Getter
public class EventsResponseDto {
    private Long id;
    private String content;
    private String reviewId;
    private String attachedPhotoIds;
    private String userId;
    private String placeId;

    public EventsResponseDto(Events entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.reviewId = entity.getReviewId();
        this.attachedPhotoIds = entity.getAttachedPhotoIds();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
    }
}
