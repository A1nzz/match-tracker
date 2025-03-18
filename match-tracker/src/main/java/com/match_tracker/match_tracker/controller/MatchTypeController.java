package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.MatchType;
import com.match_tracker.match_tracker.service.MatchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-types")
public class MatchTypeController {

    private final MatchTypeService matchTypeService;

    @Autowired
    public MatchTypeController(MatchTypeService matchTypeService) {
        this.matchTypeService = matchTypeService;
    }

    @GetMapping
    public List<MatchType> getAllMatchTypes() {
        return matchTypeService.getAllMatchTypes();
    }

    @GetMapping("/{id}")
    public MatchType getMatchTypeById(@PathVariable Long id) {
        return matchTypeService.getMatchTypeById(id);
    }
}