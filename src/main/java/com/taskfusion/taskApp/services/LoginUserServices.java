package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.Utility.HashPassword;
import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUserServices {
    @Autowired
    private UserDetailRepository _userrepo;

    public Boolean loginuser(String username, String password) {
        try {
           String passwordmsg = HashPassword.hassPassword(password);
           var userTbl = _userrepo.findbyusernameandpassword(username,passwordmsg);
           if(userTbl!=null){
               return true;
           }
           else {
               return false;
           }
        } catch (Exception ex) {
            return false;
        }
    }
}
