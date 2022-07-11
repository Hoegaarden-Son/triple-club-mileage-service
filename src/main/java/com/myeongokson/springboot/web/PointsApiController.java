package com.myeongokson.springboot.web;

import com.myeongokson.springboot.service.points.PointsService;
import com.myeongokson.springboot.service.reviews.ReviewsService;
import com.myeongokson.springboot.web.dto.points.AllPointsResponseDto;
import com.myeongokson.springboot.web.dto.points.PointsListResponseDto;
import com.myeongokson.springboot.web.dto.points.PointsSaveRequestDto;
import com.myeongokson.springboot.web.dto.points.PointsUpdateRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsResponseDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PointsApiController {

    private final PointsService pointsService;

    @PostMapping("/points")
    public Long save(@RequestBody PointsSaveRequestDto requestDto) {
        return pointsService.save(requestDto);
    }

    @PutMapping("/points/{userId}")
    public Long update(@PathVariable Long id, @RequestBody PointsUpdateRequestDto requestDto) {
        return pointsService.update(id, requestDto);
    }

    @GetMapping("/points/{userId}")
    public List<PointsListResponseDto> findAllByUserIdDesc (@PathVariable String userId) {
        return pointsService.findAllByUserIdDesc(userId);
    }

    @GetMapping("/points/all/{userId}")
    public AllPointsResponseDto findAllPointsByUserId (@PathVariable String userId) {
        return pointsService.findAllPointsByUserId(userId);
    }

    @DeleteMapping("/points/{userId}")
    public Long reviewsService (@PathVariable Long id) {
        pointsService.delete(id);
        return id;
    }
}


