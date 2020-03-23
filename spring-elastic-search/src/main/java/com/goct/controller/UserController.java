package com.goct.controller;

import com.goct.entity.User;
import com.goct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Michael");
        user.setLastName("Jackson");
        user.setAddress("New York NY");
        user.setBirthDate(Calendar.getInstance().getTime());
        user.setId("K0001");
        userRepository.save(user);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable String search) {
        List<User> users = userRepository.getByCustomQuery(search);
        // List<User> users = userRepository.findByFirstNameLikeOrLastNameLike(search, search);
        return ResponseEntity.ok(users);
    }

}
