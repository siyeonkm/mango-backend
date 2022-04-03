package com.mango.healthymango.service;

import com.mango.healthymango.domain.*;
import com.mango.healthymango.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LeaderService {
    private final LeaderTotalRepository leaderTotalRepository;
    private final LeaderScoreRepository leaderScoreRepository;
    private final UserService userService;

    public String createLeader(LeaderRequestDto leaderDto, boolean isTotal) {   //score = 0, total = 1
        String newName = leaderDto.getUsername();
        Integer newScore = leaderDto.getScore();
        User user = userService.findUser(newName);

        String answer = "";

        /*받은 닉네임으로 user에 데이터 있는지 찾고, 없으면 바로 return*/
        if(user == null) {
            return "not registered";
        }

        /*Score 리더보트에 등록하기*/
        if(isTotal == false) {
            answer = registerScore(user, newScore);
        }
        /*Total 리더보드에 등록하기*/
        else {
            answer = registerTotal(user, newScore);
        }
        return answer;
    }

    public String registerScore(User user, Integer newScore) {
        LeaderScore leaderScoreMe = leaderScoreRepository.findLeaderScoreByUser(user);  //score 리더보드에 있는지 확인
        //리더보드에 있음
        if(leaderScoreMe != null) {
            if(leaderScoreMe.getDailyScore() < newScore) {
                updateScore(leaderScoreMe, newScore);
            }
        }
        //리더보드에 없음
        else {
            List<LeaderScore> entities = leaderScoreRepository.findAll(Sort.by(Sort.Direction.ASC, "dailyScore"));
            if(entities.size() >= 1000) {
                LeaderScore lowest = entities.get(0);
                deleteLowestScore(lowest, newScore);
            }
            createScoreLeader(user, newScore);
        } return "ok";
    }

    public String registerTotal(User user, Integer newScore) {
        LeaderTotal leaderTotalMe = leaderTotalRepository.findLeaderTotalByUser(user);  //total 리더보드에 있는지 확인
        //리더보드에 있음
        if(leaderTotalMe != null) {
            if(leaderTotalMe.getTotalScore() < newScore) {
                updateTotal(leaderTotalMe, newScore);
            }
        }
        //리더보드에 없음
        else {
            List<LeaderTotal> entities = leaderTotalRepository.findAll(Sort.by(Sort.Direction.ASC, "totalScore"));
            if(entities.size() >= 1000) {
                LeaderTotal lowest = entities.get(0);
                deleteLowestTotal(lowest, newScore);
            }
            createTotalLeader(user, newScore);
        } return "ok";
    }

    public void createScoreLeader(User user, Integer newScore) {
        leaderScoreRepository.save(
                LeaderScore.builder()
                        .user(user)
                        .dailyScore(newScore)
                        .build()
        );
    }

    public void createTotalLeader(User user, Integer newScore) {
        leaderTotalRepository.save(
                LeaderTotal.builder()
                        .user(user)
                        .totalScore(newScore)
                        .build()
        );
    }

    public void updateScore(LeaderScore leaderScoreMe, Integer newScore) {
        leaderScoreMe.updateScore(newScore);
        leaderScoreRepository.save(leaderScoreMe);
    }

    public void updateTotal(LeaderTotal leaderTotalMe, Integer newScore) {
        leaderTotalMe.updateScore(newScore);
        leaderTotalRepository.save(leaderTotalMe);
    }

    public void deleteLowestScore(LeaderScore lowest, Integer newScore) {
        if (lowest.getDailyScore() < newScore) {
            leaderScoreRepository.delete(lowest);
        }
    }

    public void deleteLowestTotal(LeaderTotal lowest, Integer newScore) {
        if (lowest.getTotalScore() < newScore) {
            leaderTotalRepository.delete(lowest);
        }
    }


    /*score 리더보드 반환*/
    public List<LeaderScoreResponseDto> findScores() {
        List<LeaderScore> entities = leaderScoreRepository.findAll(Sort.by(Sort.Direction.DESC, "dailyScore"));
        List<LeaderScoreResponseDto> leaderScoreResponseDtos = new ArrayList<>();

        int size = Math.min(30, entities.size());
        for(int i = 0; i < size; i++) {
            LeaderScore leaderScore = entities.get(i);
            leaderScoreResponseDtos.add(new LeaderScoreResponseDto(leaderScore));
        }
        return leaderScoreResponseDtos;
    }

    /*total 리더보드 반환*/
    public List<LeaderTotalResponseDto> findTotals() {
        List<LeaderTotal> entities = leaderTotalRepository.findAll(Sort.by(Sort.Direction.DESC, "totalScore"));
        List<LeaderTotalResponseDto> leaderTotalResponseDtos = new ArrayList<>();

        int size = Math.min(30, entities.size());
        for(int i = 0; i < size; i++) {
            LeaderTotal leaderTotal = entities.get(i);
            leaderTotalResponseDtos.add(new LeaderTotalResponseDto(leaderTotal));
        }
        return leaderTotalResponseDtos;
    }


    public MyLeaderScoreResponseDto findScore(UserRequestDto userRequestDto) {
        Integer rank = 1;
        String username = userRequestDto.getUsername();
        User user = userService.findUser(username);

        LeaderScore tempScore = LeaderScore.builder().user(user).dailyScore(0).build();
        MyLeaderScoreResponseDto myLeaderScoreResponseDto = new MyLeaderScoreResponseDto(tempScore, 0);

        List<LeaderScore> entities = leaderScoreRepository.findAll(Sort.by(Sort.Direction.DESC, "dailyScore"));
        for(int i = 0; i < entities.size(); i++) {
            LeaderScore leaderScore = entities.get(i);
            if(i > 0) {
                LeaderScore leaderScoreBefore = entities.get(i-1);
                if(!leaderScore.getDailyScore().equals(leaderScoreBefore.getDailyScore())) rank = i+1;
            }
            if(leaderScore.getUser().getUsername().equals(username)) return new MyLeaderScoreResponseDto(leaderScore, rank);
        }
        return myLeaderScoreResponseDto;
    }

    public MyLeaderTotalResponseDto findTotal(UserRequestDto userRequestDto) {
        Integer rank = 1;
        String username = userRequestDto.getUsername();
        User user = userService.findUser(username);
        LeaderTotal tempTotal = LeaderTotal.builder().user(user).totalScore(0).build();
        MyLeaderTotalResponseDto myLeaderTotalResponseDto = new MyLeaderTotalResponseDto(tempTotal, 0);

        List<LeaderTotal> entities = leaderTotalRepository.findAll(Sort.by(Sort.Direction.DESC, "totalScore"));
        for(int i = 0; i < entities.size(); i++) {
            LeaderTotal leaderTotal = entities.get(i);
            if(i > 0) {
                LeaderTotal leaderTotalBefore = entities.get(i-1);
                if(!leaderTotal.getTotalScore().equals(leaderTotalBefore.getTotalScore())) rank = i+1;
            }
            if(leaderTotal.getUser().getUsername().equals(username)) return new MyLeaderTotalResponseDto(leaderTotal, rank);
        }
        return myLeaderTotalResponseDto;
    }
}
