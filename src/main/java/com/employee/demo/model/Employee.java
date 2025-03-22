package com.employee.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long employeeId;

    @NonNull
    private String name;
    @NonNull
    private String department;
    @NonNull
    private double salary;
    @NonNull
    private Integer status;
    @NonNull
    @Column(unique=true,nullable=false)
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String lastLogin;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id", nullable = false)
//    private Department departments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



