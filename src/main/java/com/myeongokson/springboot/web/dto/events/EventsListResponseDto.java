package com.myeongokson.springboot.web.dto.events;

import com.myeongokson.springboot.domain.events.Events;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventsListResponseDto {
    private final Long id;
    private final String type;
    private final String action;
    private final String reviewId;
    private final String userId;
    private final String placeId;
    private final String content;
    private final LocalDateTime modifiedDate;

    public EventsListResponseDto(Events entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.action = entity.getAction();
        this.reviewId = entity.getReviewId();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}
