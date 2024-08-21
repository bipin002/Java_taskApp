package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.models.roletbl;
import com.taskfusion.taskApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRole {
    @Autowired
    private RoleRepository _rolerepo;

    public roletbl FindRoleByUserId(Integer role_id) {
        return _rolerepo.FindRoleNameById(role_id);
    }
}
