package com.myeongokson.springboot.web.dto.points;

import com.myeongokson.springboot.domain.events.Events;
import com.myeongokson.springboot.domain.points.Points;
import lombok.Getter;

@Getter
public class AllPointsResponseDto {
    private String userId;
    private int totalPoint;

    public AllPointsResponseDto(String userId, int totalPoint) {
        this.userId = userId;
        this.totalPoint = totalPoint;
    }
}
