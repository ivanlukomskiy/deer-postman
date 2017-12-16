package com.ivanlukomskiy.deer.postman.service;

import com.ivanlukomskiy.deer.postman.DeerSantaException;
import com.ivanlukomskiy.deer.postman.model.PersonsSet;
import com.ivanlukomskiy.deer.postman.model.User;
import com.ivanlukomskiy.deer.postman.repository.PersonsSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 16.12.2017.
 */
@Service
public class PersonsSetService {

    @Autowired
    private PersonsSetRepository personsSetRepository;

    public PersonsSet findOne(Integer id, User user) {
        return personsSetRepository.findByIdAndUser(id, user);
    }

    public List<PersonsSet> find(User user) {
        return personsSetRepository.findByUser(user);
    }

    @Transactional
    public PersonsSet save(PersonsSet personsSet, User user) {
        if(personsSet.getId() != null) {
            if(findOne(personsSet.getId(), user) == null) {
                throw new DeerSantaException("access-denied");
            }
        }
        return personsSetRepository.save(personsSet);
    }
}
