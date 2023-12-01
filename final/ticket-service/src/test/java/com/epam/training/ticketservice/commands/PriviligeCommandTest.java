package com.epam.training.ticketservice.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.Availability;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PriviligeCommandTest {

    @Test
    void isAdmin_WhenNotAuthenticatedReturnsUnavailable() {
        PriviligeCommand command = new PriviligeCommand();

        SecurityContextHolder.clearContext();

        Availability availability = command.isAdmin();

        assertThat(availability.isAvailable(), equalTo(false));
        assertThat(availability.getReason(), equalTo("You are not signed in"));
    }


    @Test
    void isAdmin_WhenAuthenticatedAsNonAdminReturnsUnavailable() {
        PriviligeCommand command = new PriviligeCommand();

        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password",
                Collections.singleton((GrantedAuthority) () -> "ROLE_USER"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Availability availability = command.isAdmin();

        assertThat(availability.isAvailable(), equalTo(false));
        assertThat(availability.getReason(), equalTo("Permission denied"));
    }

    @Test
    void isSignIn_WhenAuthenticatedReturnsAvailable() {
        PriviligeCommand command = new PriviligeCommand();

        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password",
                Collections.singleton((GrantedAuthority) () -> "ROLE_USER"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Availability availability = command.isSignIn();

        assertThat(availability.isAvailable(), equalTo(true));
        assertThat(availability.getReason(), equalTo(null));
    }


    @Test
    void isSignIn_WhenNotAuthenticatedReturnsUnavailable() {
        PriviligeCommand command = new PriviligeCommand();

        SecurityContextHolder.clearContext();

        Availability availability = command.isSignIn();

        assertThat(availability.isAvailable(), equalTo(false));
        assertThat(availability.getReason(), equalTo("Not signed in"));
    }


    @Test
    void isSignedOut_WhenAuthenticatedReturnsUnavailable() {
        PriviligeCommand command = new PriviligeCommand();

        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password",
                Collections.singleton((GrantedAuthority) () -> "ROLE_USER"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Availability availability = command.isSignedOut();

        assertThat(availability.isAvailable(), equalTo(false));
        assertThat(availability.getReason(), equalTo("Already signed in"));
    }

    @Test
    void isSignedOut_WhenNotAuthenticatedReturnsAvailable() {
        PriviligeCommand command = new PriviligeCommand();

        SecurityContextHolder.clearContext();

        Availability availability = command.isSignedOut();

        assertTrue(availability.isAvailable());
    }
}
