package com.roastme.dao.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.roastme.dao.LeaderboardDTO;
import com.roastme.dao.RoastHistory;

public interface RoastHistoryRepository extends JpaRepository<RoastHistory, Long> {

    List<RoastHistory> findTop5ByOrderByCreatedAtDesc();

    @Query("""
        SELECT new com.roastme.dao.LeaderboardDTO(r.name, COUNT(r))
        FROM RoastHistory r
        GROUP BY r.name
        ORDER BY COUNT(r) DESC
    """)
    List<LeaderboardDTO> getLeaderboard(Pageable pageable);
}
