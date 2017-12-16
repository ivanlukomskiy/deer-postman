package com.ivanlukomskiy.deer.postman.repository;

import com.ivanlukomskiy.deer.postman.model.PersonsSet;
import com.ivanlukomskiy.deer.postman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
public interface PersonsSetRepository extends JpaRepository<PersonsSet, Integer> {

    PersonsSet findByIdAndUser(Integer id, User user);
    List<PersonsSet> findByUser(User user);
}
