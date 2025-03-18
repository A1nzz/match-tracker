package com.match_tracker.match_tracker.dto;

import com.match_tracker.match_tracker.entity.MatchType;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.entity.Tournament;
import lombok.Data;

import java.sql.Date;

@Data
public class MatchDto {

    private Long Id;

    private Integer bestOf;

    private Date matchDate;

    private MatchType matchType;

    private Team teamRadiant;

    private Team teamDire;

    private Tournament tournament;

    private Integer radiantScore;

    private Integer direScore;
}
