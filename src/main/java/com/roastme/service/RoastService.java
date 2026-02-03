package com.roastme.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.roastme.dao.LeaderboardDTO;
import com.roastme.dao.Roast;
import com.roastme.dao.RoastHistory;
import com.roastme.dao.repo.RoastHistoryRepository;
import com.roastme.dao.repo.RoastRepository;

@Service
public class RoastService {

    private final RoastRepository roastRepository;
    private final RoastHistoryRepository historyRepository;

    public RoastService(RoastRepository roastRepository,
                        RoastHistoryRepository historyRepository) {
        this.roastRepository = roastRepository;
        this.historyRepository = historyRepository;
    }

    // 🔥 Get random roast + save history
    public String getRandomRoast(String name, String type) {
        Roast roast = roastRepository.getRandomRoastByType(type);

        String finalRoast = roast.getText().replace("{name}", name);

        RoastHistory history = new RoastHistory();
        history.setName(name);
        history.setRoast(finalRoast);
        history.setType(type);
        history.setCreatedAt(LocalDateTime.now());

        historyRepository.save(history);

        return finalRoast;
    }

    // 📜 Last 5 roasts
    public List<RoastHistory> getLast5Roasts() {
        return historyRepository.findTop5ByOrderByCreatedAtDesc();
    }

    // 🏆 Leaderboard (Top 5 names)
    public List<LeaderboardDTO> getLeaderboard() {
        return historyRepository.getLeaderboard(
                PageRequest.of(0, 5)   // PageRequest implements Pageable ✅
        );
    }
}
