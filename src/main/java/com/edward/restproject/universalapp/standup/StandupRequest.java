package com.edward.restproject.universalapp.standup;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StandupRequest {
    private String yesterday;
    private String today;
    private String blockers;
    private LocalDate standupDate;
}
