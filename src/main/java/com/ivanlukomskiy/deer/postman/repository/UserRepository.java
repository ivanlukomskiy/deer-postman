package com.ivanlukomskiy.deer.postman.repository;

import com.ivanlukomskiy.deer.postman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByStatus(User.Status status);
    User findFirstByLogin(String login);
}
