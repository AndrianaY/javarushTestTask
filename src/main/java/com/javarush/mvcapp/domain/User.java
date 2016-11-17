package com.javarush.mvcapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Andriana on 14.11.2016.
 */

@Entity
@Table(name = "USERS")
public class User {
        @Id
        @Column(name = "ID")
        @GeneratedValue
        private Integer id;

        @Column(name = "Name")
        private String name;

        @Column(name = "AGE")
        private int age;

        @Column(name = "ISADMIN")
        private boolean isAdmin;

        @Column(name = "CREATEDATE")
        private Timestamp createdate;

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    // Getters and setters

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String firstname) {
                this.name = firstname;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public boolean getIsAdmin() {
                return isAdmin;
        }

        public void setIsAdmin(boolean email) {
                this.isAdmin = email;
        }

        public Timestamp getCreatedate() {
                return createdate;
        }

        public void setCreatedate(Timestamp telephone) {
                this.createdate = telephone;
        }
}
