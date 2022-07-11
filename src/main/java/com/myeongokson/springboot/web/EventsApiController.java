package com.myeongokson.springboot.web;

import com.myeongokson.springboot.service.events.EventsService;
import com.myeongokson.springboot.service.points.PointsService;
import com.myeongokson.springboot.service.reviews.ReviewsService;
import com.myeongokson.springboot.web.dto.events.EventsResponseDto;
import com.myeongokson.springboot.web.dto.events.EventsSaveRequestDto;
import com.myeongokson.springboot.web.dto.points.PointsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class EventsApiController {

    private final EventsService eventsService;
    private final ReviewsService reviewsService;
    private final PointsService pointsService;

    @PostMapping("/events")
    public String eventsHandler (@RequestBody EventsSaveRequestDto requestDto) {
        if (!requestDto.getType().equalsIgnoreCase("REVIEW"))
            return HttpStatus.BAD_REQUEST.toString();

        if (requestDto.getAction().equalsIgnoreCase("ADD")) {
            int point = 0;
            String content = requestDto.getContent();
            String attachedPhotoIds = requestDto.getAttachedPhotoIds();
            String placeId = requestDto.getPlaceId();
            String userId = requestDto.getUserId();
            String reviewId = requestDto.getReviewId();

            // 이미 등록된 장소 체크
            if (reviewsService.findByPlaceIdAndUserId(placeId, userId)>=1)
                return HttpStatus.NOT_ACCEPTABLE.toString();

            // Bonus point
            if (reviewsService.findByPlaceId(placeId) < 1)
                point += 1;
            if (content != null && !content.equals("")
                    && content.length() >= 1)
                point += 1;
            if (attachedPhotoIds != null && !attachedPhotoIds.equals("")
                    && attachedPhotoIds.split("|").length >= 1)
                point += 1;

            // review 저장
            ReviewsSaveRequestDto saveRequestDto = ReviewsSaveRequestDto.builder()
                    .reviewId(reviewId)
                    .content(content)
                    .attachedPhotoIds(attachedPhotoIds)
                    .userId(userId)
                    .placeId(placeId)
                    .build();
            reviewsService.save(saveRequestDto);

            // Point 저장
            pointsService.save(PointsSaveRequestDto.builder()
                    .placeId(placeId)
                    .reviewId(reviewId)
                    .userId(userId)
                    .point(point)
                    .build());
        }
        else if (requestDto.getAction().equalsIgnoreCase("MOD")) {
            Long id = reviewsService.findByReviewId(requestDto.getReviewId());

            // 글만 작성한 리뷰에 사진을 추가하면 1점을 부여
            // reviewService 에서, 기존 리뷰에 대한 정보 -> attachedPhoto 의 size 비교 (이전=0, 지금>=1)

            // 글과 사진이 있는 리뷰에서 사진을 모두 삭제하면 1점을 회수
            // attachedPhoto size 비교, (이전>=1, 지금=0)

            ReviewsUpdateRequestDto updateRequestDto = ReviewsUpdateRequestDto.builder()
                    .content(requestDto.getContent())
                    .attachedPhotoIds(requestDto.getAttachedPhotoIds())
                    .build();
            reviewsService.update(id, updateRequestDto);

            // 포인트 업데이트
            // pointsService, update
        }
        else if (requestDto.getAction().equalsIgnoreCase("DELETE")) {

            Long id = reviewsService.findByReviewId(requestDto.getReviewId());

            // point 회수용 조회 (review id 에 대한 point get, 및 point update)
            pointsService.findAllPointsByUserId(requestDto.getUserId());
            // point 업데이트

            // review 삭제
            reviewsService.delete(id);
        }

        return eventsService.save(requestDto).toString();
    }

    @GetMapping("/events/{id}")
    public EventsResponseDto findById (@PathVariable Long id) {
        return eventsService.findById(id);
    }

}

