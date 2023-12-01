package com.epam.training.ticketservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
    void getIdShouldReturnNull() {
        User user = new User();

        Long id = user.getId();

        assertNull(id, "Id should be null");
    }

    @Test
    void setId() {
        User user = new User();
        Long id = 1L;

        user.setId(id);

        assertEquals(id, user.getId(), "Id should be set");
    }

    @Test
    void testEqualsIdenticalUsersShouldReturnTrue() {
        User user1 = new User("user", "password", Roles.USER);
        User user2 = new User("user", "password", Roles.USER);

        boolean result = user1.equals(user2);

        assertTrue(result, "Users should be equal");
    }

    @Test
    void testEqualsDifferentUsersShouldReturnFalse() {
        User user1 = new User("user1", "password", Roles.USER);
        User user2 = new User("user2", "password", Roles.USER);

        boolean result = user1.equals(user2);

        assertFalse(result, "Users not equal");
    }

    @Test
    void canEqualIdenticalUsersShouldReturnTrue() {
        User user1 = new User("user", "password", Roles.USER);
        User user2 = new User("user", "password", Roles.USER);

        boolean result = user1.canEqual(user2);

        assertTrue(result, "Should be able to equal");
    }

    @Test
    void testHashCode_IdenticalUsers_ShouldReturnEqualHashCodes() {
        User user1 = new User("user", "password", Roles.USER);
        User user2 = new User("user", "password", Roles.USER);

        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal");
    }

    @Test
    void testHashCode_DifferentUsers_ShouldReturnDifferentHashCodes() {
        User user1 = new User("user1", "password", Roles.USER);
        User user2 = new User("user2", "password", Roles.USER);

        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        assertNotEquals(hashCode1, hashCode2, "Hash codes should not be equal");
    }

    @Test
    void testToString() {
        User user = new User("user", "password", Roles.USER);

        String result = user.toString();

        assertNotNull(result, "ToString result should not be null");
    }

    @Test
    void setUsername() {
        User user = new User();

        user.setUsername("newUsername");

        assertEquals("newUsername", user.getUsername(), "Username should be set");
    }

    @Test
    void setPassword() {
        User user = new User();

        user.setPassword("newPassword");

        assertEquals("newPassword", user.getPassword(), "Password should be set");
    }

    @Test
    void setRole() {
        User user = new User();

        user.setRole(Roles.ADMIN);

        assertEquals(Roles.ADMIN, user.getRole(), "Role should be set");
    }


}
