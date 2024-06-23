package com.java_ne.security;

import com.java_ne.exceptions.BadRequestException;
import com.java_ne.repositories.IUserRepository;
import com.java_ne.enumerations.user.EUserStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.java_ne.models.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadByUserId(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String s) throws BadRequestException {
        User user = userRepository.findUserByEmailOrUsername(s, s).orElseThrow(() -> new UsernameNotFoundException("user not found with email or username of " + s));
        if (!user.getStatus().equals(EUserStatus.ACTIVE)) {
            throw new BadRequestException("User is not active");
        }
        return UserPrincipal.create(user);
    }
}