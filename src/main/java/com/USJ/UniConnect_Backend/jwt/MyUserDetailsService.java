package com.USJ.UniConnect_Backend.jwt;

import com.USJ.UniConnect_Backend.dto.UserDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDto dto = userService.getUserByEmail(email);
            return new CustomUserDetails(dto.getId(), dto.getEmail(), dto.getName(), dto.getPassword(), dto.getProfileId(), dto.getRole(), new ArrayList<>());
        } catch (JobPortalException e) {
            e.printStackTrace();
        }
        return null;
    }
}
