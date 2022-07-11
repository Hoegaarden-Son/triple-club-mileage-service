package com.myeongokson.springboot.web;

import com.myeongokson.springboot.domain.points.Points;
import com.myeongokson.springboot.domain.reviews.Reviews;
import com.myeongokson.springboot.service.events.EventsService;
import com.myeongokson.springboot.service.points.PointsService;
import com.myeongokson.springboot.service.reviews.ReviewsService;
import com.myeongokson.springboot.web.dto.events.EventsResponseDto;
import com.myeongokson.springboot.web.dto.events.EventsSaveRequestDto;
import com.myeongokson.springboot.web.dto.points.PointsSaveRequestDto;
import com.myeongokson.springboot.web.dto.points.PointsUpdateRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

            // point policy
            if (content != null && !content.equals("") && content.length() >= 1)
                point += 1;
            else
                return HttpStatus.NOT_ACCEPTABLE.toString();

            if (parseAttachedPhotoIds(attachedPhotoIds).size() >= 1)
                point += 1;

            if (reviewsService.findByPlaceId(placeId) < 1)
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
            int point = 0;
            Boolean isToUpdatePoint = false;

            Reviews reviewInfo = reviewsService.findByReviewId(requestDto.getReviewId());
            Points pointsInfo = pointsService.findByReviewId(requestDto.getReviewId());

            Long id = reviewInfo.getId();
            int sizeOfPrevPhotoIds = parseAttachedPhotoIds(reviewInfo.getAttachedPhotoIds()).size();
            int sizeOfCurrPhotoIds = parseAttachedPhotoIds(requestDto.getAttachedPhotoIds()).size();

            // 글만 작성한 리뷰에 사진을 추가하면 1점을 부여
            if(sizeOfPrevPhotoIds == 0 && sizeOfCurrPhotoIds >= 1) {
                point = pointsInfo.getPoint() + 1;
                isToUpdatePoint = true;
            }
            else if(sizeOfPrevPhotoIds >= 1 && sizeOfCurrPhotoIds == 0) {
                point = pointsInfo.getPoint() - 1;
                isToUpdatePoint = true;
            }

            // 리뷰 수정
            ReviewsUpdateRequestDto updateRequestDto = ReviewsUpdateRequestDto.builder()
                    .content(requestDto.getContent())
                    .attachedPhotoIds(requestDto.getAttachedPhotoIds())
                    .build();
            reviewsService.update(id, updateRequestDto);

            // 포인트 업데이트
            if(isToUpdatePoint) {
                PointsUpdateRequestDto pointsUpdateRequestDto = PointsUpdateRequestDto.builder()
                        .point(point)
                        .build();
                pointsService.update(pointsInfo.getId(), pointsUpdateRequestDto);
            }
        }
        else if (requestDto.getAction().equalsIgnoreCase("DELETE")) {

            // review 삭제
            Long id = reviewsService.findByReviewId(requestDto.getReviewId()).getId();
            reviewsService.delete(id);

            // point 삭제
            Long pointId = pointsService.findByReviewId(requestDto.getReviewId()).getId();
            pointsService.delete(pointId);
        }

        return eventsService.save(requestDto).toString();
    }

    @GetMapping("/events/{id}")
    public EventsResponseDto findById (@PathVariable Long id) {
        return eventsService.findById(id);
    }

    private List<String> parseAttachedPhotoIds(String strAttachedPhotoIds) {
        List<String> attachedPhotoIdsList;
        if (strAttachedPhotoIds == null || strAttachedPhotoIds.equals("")) {
            attachedPhotoIdsList = new ArrayList<>();
            return attachedPhotoIdsList;
        }

        strAttachedPhotoIds = strAttachedPhotoIds
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll(" ", "")
                .replaceAll("\\\"", "");

        attachedPhotoIdsList = new ArrayList(Arrays.asList(strAttachedPhotoIds.split(",")));
        return attachedPhotoIdsList;
    }
}

