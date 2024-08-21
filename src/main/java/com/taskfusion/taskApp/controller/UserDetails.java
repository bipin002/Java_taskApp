package com.taskfusion.taskApp.controller;

import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.services.CreateOrUpdatePasswordServices;
import com.taskfusion.taskApp.services.CreateUserServices;
import com.taskfusion.taskApp.services.DeleteUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usercreate")

public class UserDetails {

    @Autowired
    private CreateUserServices _createuserservices;
    @Autowired
    private CreateOrUpdatePasswordServices _passwordservices;
    @Autowired
    private DeleteUserServices _deleteuserservices;

    @PostMapping("/createuser")
    public ResponseEntity<String> createNewUser(@RequestBody UserTbl userdetails) {
        try {
            String test = "create.";
            System.out.println("Before calling createuser");
            String newcUserCreate = _createuserservices.createNewUser(userdetails);
            System.out.println(newcUserCreate);
            return ResponseEntity.ok().body(newcUserCreate);

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/createPassword")
    public ResponseEntity<String> createPassword(@RequestParam Long userid,@RequestParam String newpassword) {
        try {
            String passwordmsg = _passwordservices.createPassword(userid,newpassword);
            return ResponseEntity.ok(passwordmsg);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/userdelete/{userid}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userid) {
        try {
            String test = "Is delete.";
            ResponseEntity response = _deleteuserservices.deleteUserById(userid);
            if (response.getStatusCode() == HttpStatusCode.valueOf(200)) {
                return ResponseEntity.ok().body("user deleted.");
            } else {
                return response;
            }

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
