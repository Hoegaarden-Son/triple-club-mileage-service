package com.myeongokson.springboot.service.points;

import com.myeongokson.springboot.domain.points.Points;
import com.myeongokson.springboot.domain.points.PointsRepository;
import com.myeongokson.springboot.domain.reviews.Reviews;
import com.myeongokson.springboot.web.dto.points.PointsListResponseDto;
import com.myeongokson.springboot.web.dto.points.AllPointsResponseDto;
import com.myeongokson.springboot.web.dto.points.PointsSaveRequestDto;
import com.myeongokson.springboot.web.dto.points.PointsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PointsService {
    private final PointsRepository pointsRepository;

    @Transactional
    public Long save(PointsSaveRequestDto requestDto) {
        return pointsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, PointsUpdateRequestDto requestDto) {
        Points points = pointsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 글이 없습니다. id="+ id));

        points.update(requestDto.getPoint());
        return id;
    }

    public AllPointsResponseDto findAllPointsByUserId (String userId) {
        int totalPoints = pointsRepository.findPointByUserId(userId);
        return new AllPointsResponseDto(userId, totalPoints);
    }


    @Transactional(readOnly = true)
    public List<PointsListResponseDto> findAllDesc() {
        return pointsRepository.findAllDesc().stream()
                .map(PointsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PointsListResponseDto> findAllByUserIdDesc(String userId) {
        return pointsRepository.findAllByUserIdDesc(userId).stream()
                .map(PointsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Points points = pointsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        pointsRepository.delete(points);
    }
}
