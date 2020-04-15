package com.classpath.ordermgmt.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long userId;

    private String username;

    private String password;

    private String emailAddress;

    @ManyToMany(mappedBy = "users",
            cascade = ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

}