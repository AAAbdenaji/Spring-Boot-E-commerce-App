package com.misc.sandboxproj.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.misc.sandboxproj.Helpers.CustomUserDetails;
import com.misc.sandboxproj.Repositories.UserRepository;
import com.misc.sandboxproj.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(Username).orElseThrow(() -> new UsernameNotFoundException("User not found. "));
        return new CustomUserDetails(user);
    }
}
