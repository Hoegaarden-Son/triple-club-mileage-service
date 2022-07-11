package com.myeongokson.springboot.web.dto.reviews;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewsUpdateRequestDto {
    private String content;
    private String attachedPhotoIds;

    @Builder
    public ReviewsUpdateRequestDto(String content, String attachedPhotoIds) {
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
    }
}
