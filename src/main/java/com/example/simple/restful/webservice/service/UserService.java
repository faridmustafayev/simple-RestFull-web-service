package com.example.simple.restful.webservice.service;

import com.example.simple.restful.webservice.method.User;
import com.example.simple.restful.webservice.method.UserCriteria;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final Map<UUID, User> userMap = new HashMap<>();

    public void createUser(User user) {
        var randomUUID = UUID.randomUUID();
        user.setId(randomUUID);
        userMap.put(randomUUID, user);
    }

    public void updateUser(UUID id, Integer age) {
        var existingUser = userMap.get(id);
        if (existingUser != null) {
            existingUser.setAge(age);
        }else {
            throw new RuntimeException("User not exists with the given uuid");
        }
    }

    public List<User> filteringByAge(UserCriteria userCriteria) {
        return userMap
                .values()
                .stream()
                .filter(e-> e.getAge() >= userCriteria.getFromAge() && e.getAge() <= userCriteria.getToAge())
                .toList();
    }

}
