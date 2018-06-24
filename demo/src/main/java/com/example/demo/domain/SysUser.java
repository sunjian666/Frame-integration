package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SysUser extends AbstractAuditingEntity implements UserDetails{

    private Integer id;
    private String username;
    private String password;
    private String codeArr;
    private String name;
    private String email;
    private String phone;
    private String status;

    private List<? extends GrantedAuthority> authorities;
    private List<SysRole> roles;

    public SysUser() {
    }

    public SysUser(Integer id, String username, String password, String codeArr, String name, String email, String phone, String status, List<? extends GrantedAuthority> authorities, List<SysRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.codeArr = codeArr;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.authorities = authorities;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getCodeArr() {
        return codeArr;
    }

    public void setCodeArr(String codeArr) {
        this.codeArr = codeArr;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", codeArr='" + codeArr + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", authorities=" + authorities +
                ", roles=" + roles +
                '}';
    }
}
