package com.myeongokson.springboot.domain.points;

import com.myeongokson.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TB_POINTS")
@Entity
public class Points extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, name = "USER_ID")
    private String userId;

    @Column(nullable = false, name = "REVIEW_ID")
    private String reviewId;

    @Column(nullable = false, name = "PLACE_ID")
    private String placeId;

    @Column(nullable = false, name = "POINT")
    private int point;

    @Builder
    public Points(String userId, String reviewId, String placeId, int point) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.point = point;
    }

    public void update(int point) {
        this.point = point;
    }
}