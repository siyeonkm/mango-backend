package com.mango.healthymango.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;

    private String username;

    @Builder
    public User(String username) {
        this.username = username;
    }
}
