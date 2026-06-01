package com.edward.restproject.universalapp.standup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StandupService {

    private final StandupRepository repo;

    public StandupSummary submit(StandupRequest req) {
        StandupEntry entry = new StandupEntry();
        entry.setYesterday(req.getYesterday());
        entry.setToday(req.getToday());
        entry.setBlockers(req.getBlockers());
        entry.setStandupDate(req.getStandupDate() != null ? req.getStandupDate() : LocalDate.now());
        return StandupSummary.from(repo.save(entry));
    }

    public StandupSummary todaySummary() {
        return repo.findTopByStandupDateOrderBySubmittedAtDesc(LocalDate.now())
                .map(StandupSummary::from)
                .orElseThrow(() -> new StandupNotFoundException("No standup submitted for today yet."));
    }

    public List<StandupSummary> history() {
        return repo.findAllByOrderBySubmittedAtDesc()
                .stream()
                .map(StandupSummary::from)
                .toList();
    }

    public StandupSummary getById(Long id) {
        return repo.findById(id)
                .map(StandupSummary::from)
                .orElseThrow(() -> new StandupNotFoundException("Standup #" + id + " not found."));
    }
}
