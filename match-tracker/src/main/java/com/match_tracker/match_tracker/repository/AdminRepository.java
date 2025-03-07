package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
