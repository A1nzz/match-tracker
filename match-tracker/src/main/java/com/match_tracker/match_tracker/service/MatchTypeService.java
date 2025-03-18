package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.MatchType;
import com.match_tracker.match_tracker.repository.MatchTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchTypeService {

    private final MatchTypeRepository matchTypeRepository;

    @Autowired
    public MatchTypeService(MatchTypeRepository matchTypeRepository) {
        this.matchTypeRepository = matchTypeRepository;
    }

    public List<MatchType> getAllMatchTypes() {
        return matchTypeRepository.findAll();
    }

    public MatchType getMatchTypeById(Long id) {
        return matchTypeRepository.findById(id).orElse(null);
    }

    public MatchType saveMatchType(MatchType matchType) {
        return matchTypeRepository.save(matchType);
    }

    public void deleteMatchType(Long id) {
        matchTypeRepository.deleteById(id);
    }
}