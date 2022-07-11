package com.myeongokson.springboot.web.dto.events;

import com.myeongokson.springboot.domain.events.Events;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class EventsRequestDto {
    private String type;
    private String action;
    private String reviewId;
    private String content;
    private String attachedPhotoIds;
    private String userId;
    private String placeId;

    @Builder
    public EventsRequestDto(String type, String action, String reviewId, String content, String attachedPhotoIds, String userId, String placeId) {
        this.type = type;
        this.action = action;
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public Events toEntity() {
        return Events.builder()
                .type(type)
                .action(action)
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build();
    }
}
