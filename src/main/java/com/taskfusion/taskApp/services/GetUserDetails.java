package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetails {
    @Autowired
    private UserDetailRepository _userepo;

    public UserTbl getUserDetails(String username){
        return _userepo.fingbyusername(username);
    }
}
