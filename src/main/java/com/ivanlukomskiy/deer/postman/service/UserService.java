package com.ivanlukomskiy.deer.postman.service;

import com.ivanlukomskiy.deer.postman.DeerSantaException;
import com.ivanlukomskiy.deer.postman.model.User;
import com.ivanlukomskiy.deer.postman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findByStatus(User.Status.ACTIVE);
    }

    public User getUser(int id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User createUser(User user) {
        if (findByLogin(user.getLogin()) != null) {
            throw new DeerSantaException("login-conflict");
        }
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    @Transactional
    public void removeUser(Integer id) {
        User user = userRepository.findOne(id);
        user.setStatus(User.Status.DELETED);
        userRepository.save(user);
    }
}
