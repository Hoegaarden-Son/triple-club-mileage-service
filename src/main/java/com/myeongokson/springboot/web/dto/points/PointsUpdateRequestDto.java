package com.myeongokson.springboot.web.dto.points;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointsUpdateRequestDto {
    private int point;

    @Builder
    public PointsUpdateRequestDto(int point) {
        this.point = point;
    }
}
