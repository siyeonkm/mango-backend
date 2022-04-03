package com.mango.healthymango.service;

import com.mango.healthymango.domain.User;
import com.mango.healthymango.domain.UserRepository;
import com.mango.healthymango.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean checkAvailability(UserRequestDto userdto) {
        String username = userdto.getUsername();
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public User createUser(UserRequestDto userdto) {
        String username = userdto.getUsername();
        User newUser = User.builder()
                .username(username)
                .build();
        userRepository.save(
                newUser
        );
        return newUser;
    }

    public User findUser(String username) {
        User user = userRepository.findUserByUsername(username);
        UserRequestDto userdto = new UserRequestDto("익명");
        return user;
    }
}
