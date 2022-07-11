package com.myeongokson.springboot.service.reviews;

import com.myeongokson.springboot.domain.reviews.Reviews;
import com.myeongokson.springboot.domain.reviews.ReviewsRepository;
import com.myeongokson.springboot.web.dto.reviews.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    @Transactional
    public Long save(ReviewsSaveRequestDto requestDto) {
        return reviewsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, ReviewsUpdateRequestDto requestDto) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 글이 없습니다. id="+ id));

        reviews.update(requestDto.getContent());
        return id;
    }

    public ReviewsResponseDto findById (Long id) {
        Reviews entity = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id="+ id));

        return new ReviewsResponseDto(entity);
    }

    public Long findByReviewId (String reviewId) {
        Reviews entity = reviewsRepository.findByReviewId(reviewId);
        return entity.getId();
    }

    public int findByPlaceId (String placeId) {
        int count = reviewsRepository.findByPlaceId(placeId);
        return count;
    }

    public int findByPlaceIdAndUserId (String placeId, String userId) {
        int count = reviewsRepository.findByPlaceIdAndUserId(placeId, userId);
        return count;
    }

    @Transactional(readOnly = true)
    public List<ReviewsListResponseDto> findAllDesc() {
        return reviewsRepository.findAllDesc().stream()
                .map(ReviewsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Reviews reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        reviewsRepository.delete(reviews);
    }
}
