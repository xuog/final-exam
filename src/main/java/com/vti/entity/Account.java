package com.vti.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 50, updatable = false)
    private String username;

    @Column(name = "password", length = 72, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Formula(value = "concat_ws(' ', first_name, last_name)")
    private String fullName;

    @Column(name = "role", length = 8, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public enum Role{
        ADMIN, MANAGER, EMPLOYEE
    }
}
