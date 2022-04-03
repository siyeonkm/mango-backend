package com.mango.healthymango.web;

import com.mango.healthymango.domain.User;
import com.mango.healthymango.service.UserService;
import com.mango.healthymango.web.dto.LeaderRequestDto;
import com.mango.healthymango.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user/registration")
    public String register(@RequestBody UserRequestDto userdto) {
        boolean isAvailable = userService.checkAvailability(userdto);
        if(isAvailable) {
            userService.createUser(userdto);
            return "registered";
        }
        else
        {
            return "exist";
        }
    }

    @PostMapping("/api/user")
    public String findUser(@RequestBody UserRequestDto userdto) {
        boolean isAvailable = userService.checkAvailability(userdto);
        if(isAvailable) {
            return "none";
        }
        else
        {
            return "exist";
        }
    }

    @GetMapping("/api/server/connection")
    public String checkConnection() {
        return "ok";
    }

    @GetMapping("api/time")
    public String returnTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String formattedString = nowKST.format(formatter);
        return formattedString;
    }

    @GetMapping("api/version")
    public String returnVersion() {
        String version = "1.6.0";
        return version;
    }
}
