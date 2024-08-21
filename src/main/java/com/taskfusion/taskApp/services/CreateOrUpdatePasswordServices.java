package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.Utility.HashPassword;
import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class CreateOrUpdatePasswordServices {
    @Autowired
    private UserDetailRepository _userrepo;

    public String createPassword(Long userid, String newpassword) {
        String passwordmsg;
        Date updatedate = new Date();
        try {

            if (_userrepo.existsById(userid)) {
                passwordmsg = HashPassword.hassPassword(newpassword);//"Password is created.";
                Optional<UserTbl> userTbl = _userrepo.findById(userid);
                if (userTbl.isPresent()) {
                    UserTbl usertbl2=userTbl.get();
//                    userTbl.get().setPassword(passwordmsg);
//                    userTbl.get().setUpdated_at(updatedate);
                    usertbl2.setPassword(passwordmsg);
                    usertbl2.setUpdated_at(updatedate);
                    _userrepo.save(usertbl2);
                }

            } else {
                passwordmsg = "User not found.";
            }
            return passwordmsg;
        } catch (Exception ex) {
            passwordmsg = "Something went wrong.";
            return passwordmsg;
        }
    }
}
