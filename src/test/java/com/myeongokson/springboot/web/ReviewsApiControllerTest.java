package com.myeongokson.springboot.web;

import com.myeongokson.springboot.domain.reviews.Reviews;
import com.myeongokson.springboot.domain.reviews.ReviewsRepository;
import com.myeongokson.springboot.web.dto.reviews.ReviewsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Test
    public void Reviews_등록된다() throws Exception {
        //given
        String reviewId = "test-id";
        String content = "테스트 본문";
        String attachedPhotoIds = "test-attachedPhotoIds";
        String userId = "test-userId";
        String placeId = "test-placeId";

        ReviewsSaveRequestDto requestDto = ReviewsSaveRequestDto.builder()
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build();

        String url = "http://localhost:" + port + "/events";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Reviews> all = reviewsRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Reviews_수정된다() throws Exception {
        //given
        Reviews savedReviews = reviewsRepository.save(Reviews.builder()
                .reviewId("reviewId")
                .content("content")
                .attachedPhotoIds("attachedPhotoIds")
                .userId("userId")
                .placeId("placeId")
                .build());

        Long updateId = savedReviews.getId();
        String expectedContent = "content2";

        ReviewsUpdateRequestDto requestDto = ReviewsUpdateRequestDto.builder()
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/events/" + updateId;

        HttpEntity<ReviewsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Reviews> all = reviewsRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void Reviews_삭제된다() throws Exception {
        //given
        Reviews savedReviews = reviewsRepository.save(Reviews.builder()
                .reviewId("reviewId")
                .content("content")
                .attachedPhotoIds("attachedPhotoIds")
                .userId("userId")
                .placeId("placeId")
                .build());

        Long deleteId = savedReviews.getId();

        ReviewsUpdateRequestDto requestDto = ReviewsUpdateRequestDto.builder()
                .content(savedReviews.getContent())
                .build();

        String url = "http://localhost:" + port + "/events/" + deleteId;

        HttpEntity<ReviewsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Reviews> all = reviewsRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }
}
