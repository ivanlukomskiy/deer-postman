package com.ivanlukomskiy.deer.postman.service;

import com.ivanlukomskiy.deer.postman.DeerSantaException;
import com.ivanlukomskiy.deer.postman.model.Dispatch;
import com.ivanlukomskiy.deer.postman.model.User;
import com.ivanlukomskiy.deer.postman.repository.DispatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 15.12.2017.
 */
@Service
public class DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;

    public List<Dispatch> getDispatches(User user) {
        return dispatchRepository.findDispatchByUser(user);
    }

    public Dispatch getDispatch(Integer id, User user) {
        return dispatchRepository.findByIdAndUser(id, user);
    }

    public Dispatch create(User user) {
        return dispatchRepository.save(new Dispatch(user));
    }

    @Transactional
    public Dispatch save(Dispatch dispatch, User user) {
        if (dispatch.getId() != null) {
            if (getDispatch(dispatch.getId(), user) == null) {
                throw new DeerSantaException("access-denied");
            }
        }
        return dispatchRepository.save(dispatch);
    }
}
