package com.USJ.UniConnect_Backend.jwt;

import com.USJ.UniConnect_Backend.dto.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private  Long id;
    private String username;
    private String name;
    private String password;
    private Long profileId;
    private UserRole userRole;
    private Collection<?extends GrantedAuthority> authorities;

}
