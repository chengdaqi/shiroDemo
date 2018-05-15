package com.shiroTest.demo.model;


import java.util.HashSet;
import java.util.Set;

public class Role {
    private Integer rid;
    private String rname;
    Set<com.shiroTest.demo.model.Permission> permissions = new HashSet<>();
    Set<User> users = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
