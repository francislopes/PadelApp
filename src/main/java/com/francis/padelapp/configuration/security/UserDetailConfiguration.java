package com.francis.padelapp.configuration.security;

import com.francis.padelapp.model.User;
import com.francis.padelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailConfiguration implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailConfiguration(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var lowerCaseUsername = username.toLowerCase();

        var loggedUser = repository
                .findOneByUsernameIgnoreCase(lowerCaseUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return new org.springframework.security.core.userdetails
                .User(loggedUser.getUsername(), loggedUser.getPassword(), getAuthorities(loggedUser));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName().name()));
        String[] userRoles = roles.stream().toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
