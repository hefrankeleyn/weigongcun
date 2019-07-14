package com.edm.edmfetchdataplatform.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
public class UserDetailsLogin implements UserDetails, Serializable {

    private static final Long serialVersionUID = -211186125L;


    private Edmer edmer;

    public UserDetailsLogin(Edmer edmer){
        this.edmer = edmer;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = edmer.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (roles!=null && !roles.isEmpty()){
            for (Role role :
                    roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return edmer.getPassword();
    }

    @Override
    public String getUsername() {
        return edmer.getEmail();
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

    @Override
    public String toString() {
        return "UserDetailsLogin{" +
                "edmer=" + edmer +
                '}';
    }
}
