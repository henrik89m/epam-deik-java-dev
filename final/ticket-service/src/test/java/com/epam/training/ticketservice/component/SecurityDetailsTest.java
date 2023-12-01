package com.epam.training.ticketservice.component;

import com.epam.training.ticketservice.model.Roles;
import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityDetailsTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SecurityDetails securityDetails;

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        String username = "testUser";
        String password = "testPassword";
        Roles role = Roles.USER;
        User user = new User(username, password, role);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = securityDetails.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
        assertFalse(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_AdminExists_ReturnsUserDetailsWithAdminRole() {
        String username = "adminUser";
        String password = "adminPassword";
        Roles role = Roles.ADMIN;
        User adminUser = new User(username, password, role);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(adminUser));

        UserDetails userDetails = securityDetails.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        String nonExistentUsername = "notExist";
        when(userRepository.findByUsername(nonExistentUsername)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> securityDetails.loadUserByUsername(nonExistentUsername));
    }
}
