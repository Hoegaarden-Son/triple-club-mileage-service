package com.myeongokson.springboot.domain.reviews;

import com.myeongokson.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TB_REVIEWS")
@Entity
public class Reviews extends BaseTimeEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(nullable = false, name="REVIEW_ID")
    private String reviewId;

    @Column(columnDefinition = "TEXT", nullable = false, name="CONTENT")
    private String content;

    @Column(nullable = false, name="ATTACHED_PHOTO_IDS")
    private String attachedPhotoIds;

    @Column(nullable = false, name="USER_ID")
    private String userId;

    @Column(nullable = false, name="PLACE_ID")
    private String placeId;

    @Builder
    public Reviews(String reviewId, String content, String attachedPhotoIds, String userId, String placeId) {
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public void update(String content) {
        this.content = content;
    }
}
