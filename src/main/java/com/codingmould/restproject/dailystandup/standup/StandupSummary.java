package com.codingmould.restproject.dailystandup.standup;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StandupSummary {
    private Long id;
    private LocalDate standupDate;
    private LocalDateTime submittedAt;
    private String yesterday;
    private String today;
    private String blockers;
    private String formatted;

    static StandupSummary from(StandupEntry entry) {
        StandupSummary s = new StandupSummary();
        s.id = entry.getId();
        s.standupDate = entry.getStandupDate();
        s.submittedAt = entry.getSubmittedAt();
        s.yesterday = entry.getYesterday();
        s.today = entry.getToday();
        s.blockers = entry.getBlockers();
        s.formatted = format(entry);
        return s;
    }

    private static String format(StandupEntry e) {
        boolean hasBlockers = e.getBlockers() != null && !e.getBlockers().isBlank();
        return """
                === Daily Standup — %s ===

                Yesterday:
                  %s

                Today:
                  %s

                Blockers:
                  %s
                """.formatted(
                e.getStandupDate(),
                bullet(e.getYesterday()),
                bullet(e.getToday()),
                hasBlockers ? bullet(e.getBlockers()) : "None"
        );
    }

    private static String bullet(String text) {
        if (text == null || text.isBlank()) return "(none)";
        return text.lines()
                .map(String::trim)
                .filter(l -> !l.isEmpty())
                .map(l -> "• " + l)
                .reduce((a, b) -> a + "\n  " + b)
                .orElse("(none)");
    }
}
