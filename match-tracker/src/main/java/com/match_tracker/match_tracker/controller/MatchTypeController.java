package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.MatchType;
import com.match_tracker.match_tracker.service.MatchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-types")
public class MatchTypeController {

    @Autowired
    private MatchTypeService matchTypeService;

    @GetMapping
    public List<MatchType> getAllMatchTypes() {
        return matchTypeService.getAllMatchTypes();
    }

    @GetMapping("/{id}")
    public MatchType getMatchTypeById(@PathVariable Long id) {
        return matchTypeService.getMatchTypeById(id);
    }

    @PostMapping
    public MatchType createMatchType(@RequestBody MatchType matchType) {
        return matchTypeService.saveMatchType(matchType);
    }

    @PutMapping("/{id}")
    public MatchType updateMatchType(@PathVariable Long id, @RequestBody MatchType matchType) {
        matchType.setId(id);
        return matchTypeService.saveMatchType(matchType);
    }

    @DeleteMapping("/{id}")
    public void deleteMatchType(@PathVariable Long id) {
        matchTypeService.deleteMatchType(id);
    }
}
