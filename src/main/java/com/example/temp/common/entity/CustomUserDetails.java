package com.example.temp.common.entity;

import com.example.temp.baseball.domain.Team;

import com.example.temp.user.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomUserDetails implements UserDetails {
    private final User user;

    public static UserDetails create(User user) {
        return new CustomUserDetails(user);
    }

    public long getId() {
        return this.user.getId();
    }


    public Team getFavoriteTeam() {
        return this.user.getFavoriteTeam();
    }

    public boolean isAdmin() {
        return this.user.getUserRole().isAdmin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

}
