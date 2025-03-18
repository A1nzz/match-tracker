package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.HomePageDto;
import com.match_tracker.match_tracker.service.HomePageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomePageController {

    private final HomePageService homePageService;

    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping
    public HomePageDto getHomePageData() {
        return homePageService.getHomePageData();
    }
}