package com.javarush.mvcapp.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Andriana on 14.11.2016.
 */

@Entity
@Indexed
@Table(name = "USERS")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        @Column(name = "ID")
        private Integer id;
        @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
        @NotNull
        @Column(name = "Name")
        private String name;
        @NotNull
        @Column(name = "AGE")
        private Integer age;
        @NotNull
        @Column(name = "ISADMIN")
        private Boolean isAdmin;
        @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
        @NotNull
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

        public Integer getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public Boolean getIsAdmin() {
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
