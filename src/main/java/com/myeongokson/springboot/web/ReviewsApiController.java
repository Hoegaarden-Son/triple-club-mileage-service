package com.myeongokson.springboot.web;

import com.myeongokson.springboot.service.reviews.ReviewsService;
import com.myeongokson.springboot.web.dto.reviews.ReviewsResponseDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ReviewsApiController {

    private final ReviewsService reviewsService;

    @PostMapping("/reviews")
    public Long save(@RequestBody ReviewsSaveRequestDto requestDto) {
        return reviewsService.save(requestDto);
    }

    @PutMapping("/reviews/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewsUpdateRequestDto requestDto) {
        return reviewsService.update(id, requestDto);
    }

    @GetMapping("/reviews/{id}")
    public ReviewsResponseDto findById (@PathVariable Long id) {
        return reviewsService.findById(id);
    }

    @DeleteMapping("/reviews/{id}")
    public Long reviewsService (@PathVariable Long id) {
        reviewsService.delete(id);
        return id;
    }
}


