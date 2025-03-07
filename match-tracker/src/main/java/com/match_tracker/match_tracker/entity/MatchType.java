package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MatchType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeName;
}
