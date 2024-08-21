package com.taskfusion.taskApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class roletbl {
    @Id
    private Integer roleid;
    private String rolename;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private Boolean status;
}
