package com.springsecurity.auth.dao;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String role_name ;
    private Boolean active;
    private Date created_date ;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Override
    public String getAuthority() {

        System.out.println(" getAuthority >>> "+role_name);
        return role_name;
    }

    @Override
    public String toString() {
        return "ID :"+id +" Role :"+role_name;
    }
}
