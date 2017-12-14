package com.ivanlukomskiy.deer.postman.repository;

import com.ivanlukomskiy.deer.postman.model.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
public interface DispatchRepository extends JpaRepository<Dispatch, Integer> {
}