package com.myeongokson.springboot.domain.reviews;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewsRepositoryTest {

    @Autowired
    ReviewsRepository reviewsRepository;

    @After
    public void cleanup() {
        reviewsRepository.deleteAll();
    }

    @Test
    public void 리뷰글저장_불러오기() {
        //given
        String reviewId = "test-id";
        String content = "테스트 본문";
        String attachedPhotoIds = "test-attachedPhotoIds";
        String userId = "test-userId";
        String placeId = "test-placeId";

        reviewsRepository.save(Reviews.builder()
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build());

        //when
        List<Reviews> reviewsList = reviewsRepository.findAll();

        //then
        Reviews reviews = reviewsList.get(0);
        assertThat(reviews.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 7, 9, 0,0,0);
        reviewsRepository.save(Reviews.builder()
                .reviewId("reviewId")
                .content("content")
                .attachedPhotoIds("attachedPhotoIds")
                .userId("userId")
                .placeId("placeId")
                .build());

        //when
        List<Reviews> postsList = reviewsRepository.findAll();

        //then
        Reviews reviews = postsList.get(0);

        System.out.println(">>>>>>> createDate="+reviews.getCreatedDate()
                +", modifiedDate="+reviews.getModifiedDate());
        assertThat(reviews.getCreatedDate()).isAfter(now);
        assertThat(reviews.getModifiedDate()).isAfter(now);
    }
}
