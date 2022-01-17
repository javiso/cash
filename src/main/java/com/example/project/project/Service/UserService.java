package com.example.project.project.Service;

import com.example.project.project.model.User;
import com.example.project.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(final User user) {
        log.info("Saving user: firstName: {}, lastName: {} and Email: {} ", user.getFirstName(), user.getLastName(), user.getEmail());
        return userRepository.save(user);
    }

    public User findById(final Long idUser) {
        log.info("Fetching user whose id is: {} ", idUser);
        return userRepository.findById(idUser).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void deleteUser(final Long idUser) {
        log.info("Deleting user whose id is: {}", idUser);
        userRepository.deleteById(idUser);
    }
}