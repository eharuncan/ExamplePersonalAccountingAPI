package com.personalaccounting.api.domain;

import java.util.Objects;

import javax.persistence.*;

import com.personalaccounting.api.enums.UserTypes;

@Entity
@Table(name = "users")
public class User {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private UserTypes type;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User() {}

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id)
                && Objects.equals(this.type, user.type)
                && Objects.equals(this.name, user.name)
                && Objects.equals(this.surname, user.surname)
                && Objects.equals(this.email, user.email)
                && Objects.equals(this.password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.name, this.surname, this.email, this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", type='" + this.type + '\'' + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + ", email='" + this.email + '\'' + ", password='" + this.password + '\'' + '}';
    }
}
