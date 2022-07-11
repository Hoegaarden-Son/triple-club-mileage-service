package com.myeongokson.springboot.service.events;

import com.myeongokson.springboot.domain.events.Events;
import com.myeongokson.springboot.domain.events.EventsRepository;
import com.myeongokson.springboot.domain.reviews.Reviews;
import com.myeongokson.springboot.web.dto.events.EventsListResponseDto;
import com.myeongokson.springboot.web.dto.events.EventsResponseDto;
import com.myeongokson.springboot.web.dto.events.EventsSaveRequestDto;
import com.myeongokson.springboot.web.dto.reviews.ReviewsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventsService {
    private final EventsRepository eventsRepository;

    @Transactional
    public Long save(EventsSaveRequestDto requestDto) {
        return eventsRepository.save(requestDto.toEntity()).getId();
    }

    public EventsResponseDto findById (Long id) {
        Events entity = eventsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 없습니다. id="+ id));

        return new EventsResponseDto(entity);
    }

    public Long findByReviewId (String reviewId) {
        Events entity = eventsRepository.findByReviewId(reviewId);
        return entity.getId();
    }

    @Transactional(readOnly = true)
    public List<EventsListResponseDto> findAllDesc() {
        return eventsRepository.findAllDesc().stream()
                .map(EventsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Events events = eventsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 없습니다. id=" + id));
        eventsRepository.delete(events);
    }
}
