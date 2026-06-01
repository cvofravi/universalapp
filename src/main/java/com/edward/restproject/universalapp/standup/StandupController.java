package com.edward.restproject.universalapp.standup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/standup")
@RequiredArgsConstructor
public class StandupController {

    private final StandupService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StandupSummary submit(@RequestBody StandupRequest request) {
        return service.submit(request);
    }

    @GetMapping("/today")
    public StandupSummary today() {
        return service.todaySummary();
    }

    @GetMapping("/history")
    public List<StandupSummary> history() {
        return service.history();
    }

    @GetMapping("/{id}")
    public StandupSummary getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
