package com.myeongokson.springboot.web;

import com.myeongokson.springboot.service.events.EventsService;
import com.myeongokson.springboot.service.reviews.ReviewsService;
import com.myeongokson.springboot.web.dto.reviews.ReviewsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ReviewsService reviewsService;
    private final EventsService eventsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("reviews", reviewsService.findAllDesc());
        return "index";
    }

    @GetMapping("/events-log")
    public String events(Model model) {
        model.addAttribute("events", eventsService.findAllDesc());
        return "events";
    }

    @GetMapping("/reviews/save")
    public String reviewsSave() {
        return "reviews-save";
    }

    @GetMapping("/reviews/update/{id}")
    public String reviewsUpdate(@PathVariable Long id, Model model) {
        ReviewsResponseDto dto = reviewsService.findById(id);
        model.addAttribute("review", dto);

        return "reviews-update";
    }
}

