package edu.depaul.cdm.se452.grouppp.cakebook.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addUser(User user) {
        try {
            Optional<User> searchUser = userRepository.findByUsername(user.getUsername());
            if (searchUser.isPresent()) {
                return new ResponseEntity<>("A user with this username already exists.", HttpStatus.CONFLICT);
            }

            userRepository.save(user);
            return new ResponseEntity<>("User created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);

        }
    }

    public ResponseEntity<Object> login(User user) {
        Optional<User> searchUser = userRepository.findByUsername(user.getUsername());
        if (searchUser.isEmpty()) {
            return new ResponseEntity<>("Username does not exist.", HttpStatus.BAD_REQUEST);
        }

        if (!user.getPword().equals(searchUser.get().getPword())) {
            return new ResponseEntity<>("Incorrect password.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(searchUser, HttpStatus.OK);

    }
}
