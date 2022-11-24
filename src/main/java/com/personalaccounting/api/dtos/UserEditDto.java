package com.personalaccounting.api.dtos;

import com.personalaccounting.api.enums.UserTypes;

public class UserEditDto {

    private final Long id;
    private UserTypes type;
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserEditDto(Long id, UserTypes type, String name, String surname, String email, String password) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
