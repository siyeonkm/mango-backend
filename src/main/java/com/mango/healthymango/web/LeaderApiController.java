package com.mango.healthymango.web;

import com.mango.healthymango.service.LeaderService;
import com.mango.healthymango.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LeaderApiController {
    private final LeaderService leaderService;

    @PostMapping("/api/leaders/score/me")
    public MyLeaderScoreResponseDto findScoreByUser(@RequestBody UserRequestDto username) {
        return leaderService.findScore(username);
    }

    @PostMapping("/api/leaders/total/me")
    public MyLeaderTotalResponseDto findTotalByUser(@RequestBody UserRequestDto username) {
        return leaderService.findTotal(username);
    }

    @GetMapping("/api/leaders/score")
    public List<LeaderScoreResponseDto> findAllByScore() {
        return leaderService.findScores();
    }

    @PostMapping("/api/leaders/score/registration")
    public String createScoreLeader(@RequestBody LeaderRequestDto leaderDto) {
        return leaderService.createLeader(leaderDto, false);
    }

    @GetMapping("/api/leaders/total")
    public List<LeaderTotalResponseDto> findAllByTotal() {
        return leaderService.findTotals();
    }

    @PostMapping("/api/leaders/total/registration")
    public String createTotalLeader(@RequestBody LeaderRequestDto leaderDto) {
        return leaderService.createLeader(leaderDto, true);
    }


}
