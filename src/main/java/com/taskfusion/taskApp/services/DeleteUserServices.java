package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserServices {
    @Autowired
    private UserDetailRepository _userrepo;

    public ResponseEntity deleteUserById(Long userid){
        if(_userrepo.existsById(userid)){
            _userrepo.deleteById(userid);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
