package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class CreateUserServices {

    @Autowired
    private UserDetailRepository _userrepo;


    public String createNewUser(UserTbl newuser) {
        //for create date
        Date currentDateTime = new Date();
        newuser.setCreated_at(currentDateTime);
        newuser.setStatus(true);
        UserTbl userNewcreate = _userrepo.save(newuser);
        String result = "new user has been created.";
        return result;
    }
}
