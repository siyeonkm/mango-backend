package com.mango.healthymango.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequestDto {
    private String username;

    public UserRequestDto(String username) {
        this.username = username;
    }
}
