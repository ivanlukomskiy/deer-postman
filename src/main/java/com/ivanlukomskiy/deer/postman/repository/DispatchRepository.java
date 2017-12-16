package com.ivanlukomskiy.deer.postman.repository;

import com.ivanlukomskiy.deer.postman.model.Dispatch;
import com.ivanlukomskiy.deer.postman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
public interface DispatchRepository extends JpaRepository<Dispatch, Integer> {

    List<Dispatch> findDispatchByUser(User user);

    Dispatch findByIdAndUser(Integer id, User user);
}