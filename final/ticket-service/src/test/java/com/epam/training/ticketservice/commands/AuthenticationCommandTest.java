package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.dto.UserDto;
import com.epam.training.ticketservice.model.Roles;
import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationCommandTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationCommand authenticationCommand;

    @Test
    void signup_Success() {
        String username = "testUser";
        String password = "testPassword";

        String result = authenticationCommand.signup(username, password);

        assertEquals("Sign up was successful", result);
    }

    @Test
    void signup_Failure() {
        String username = "testUser";
        String password = "testPassword";
        String expectedErrorMessage = "Sign up failed";

        doThrow(new IllegalArgumentException(expectedErrorMessage)).when(authenticationService).signup(username, password);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> authenticationCommand.signup(username, password));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void loginAdmin_Success() {
        String username = "admin";
        String password = "adminPassword";

        User adminUser = new User(username, password, Roles.ADMIN);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(adminUser));

        String result = authenticationCommand.loginAdmin(username, password);

        assertEquals("Successfully signed in as admin", result);
    }

    @Test
    void loginAdmin_InvalidCredentials() {
        String username = "testUser";
        String password = "testPassword";

        User regularUser = new User(username, password, Roles.USER);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(regularUser));

        String result = authenticationCommand.loginAdmin(username, password);

        assertEquals("Invalid credentials or not an admin user", result);
    }

    @Test
    void loginAdmin_UserNotFound() {
        String username = "tesUser";
        String password = "testPassword";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        String result = authenticationCommand.loginAdmin(username, password);

        assertEquals("User not found", result);
    }

    @Test
    void login_Success() {
        String username = "testUser";
        String password = "testPassword";

        when(authenticationService.signIn(username, password, false))
                .thenReturn(Optional.of(new UserDto(username, Roles.USER)));

        String result = authenticationCommand.login(username, password);

        assertEquals("Login success", result);
    }

    @Test
    void login_Failure() {
        String username = "testUser";
        String password = "testPassword";

        when(authenticationService.signIn(username, password, false))
                .thenThrow(new IllegalArgumentException("Login failed"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> authenticationCommand.login(username, password));
        assertEquals("Login failed", exception.getMessage());
    }

    @Test
    void logout_Success() {
        Mockito.doNothing().when(authenticationService).logout();

        String result = authenticationCommand.logout();

        assertEquals("Sign out was successful", result);
    }

    @Test
    void logout_Failure() {
        doThrow(new IllegalArgumentException("Logout failed")).when(authenticationService).logout();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> authenticationCommand.logout());
        assertEquals("Logout failed", exception.getMessage());
    }


}
