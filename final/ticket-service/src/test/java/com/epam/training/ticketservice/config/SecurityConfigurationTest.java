package com.epam.training.ticketservice.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SecurityConfigurationTest {
    @InjectMocks
    private SecurityConfiguration securityConfiguration;

    @Test
    void passwordEncoderCreatesNotNullPassword() {
        PasswordEncoder result = securityConfiguration.passwordEncoder();

        assertNotNull(result);
        assertTrue(result instanceof BCryptPasswordEncoder);
    }


}
