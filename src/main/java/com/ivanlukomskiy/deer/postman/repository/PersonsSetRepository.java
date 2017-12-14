package com.ivanlukomskiy.deer.postman.repository;

import com.ivanlukomskiy.deer.postman.model.PersonsSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
public interface PersonsSetRepository extends JpaRepository<PersonsSet, Integer> {
}
