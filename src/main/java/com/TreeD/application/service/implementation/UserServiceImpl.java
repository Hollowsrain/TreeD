package com.TreeD.application.service.implementation;

import com.TreeD.application.exceptions.UserNotFoundException;
import com.TreeD.application.model.entity.User;
import com.TreeD.application.repository.UserRepository;
import com.TreeD.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("User with id: " + id + " can't be retrieved");
            throw new UserNotFoundException("User can't be retrieved");
        }
        return user.get();
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setId(null);
        user.setModifiedAt(ZonedDateTime.now());
        user.setCreatedAt(ZonedDateTime.now());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUserById(Long id, User user) {
        User currentUser = getUserById(id);

        user.setId(currentUser.getId());
        user.setModifiedAt(ZonedDateTime.now());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
