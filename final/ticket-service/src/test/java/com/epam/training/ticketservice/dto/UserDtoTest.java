package com.epam.training.ticketservice.dto;

import com.epam.training.ticketservice.model.Roles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testEquals_UserDtosShouldReturnTrueWhenTheyAreEqual() {
        UserDto userDto1 = UserDto.builder().username("user").role(Roles.USER).build();
        UserDto userDto2 = UserDto.builder().username("user").role(Roles.USER).build();

        boolean result = userDto1.equals(userDto2);

        assertTrue(result, "UserDtos should be equal");
    }

    @Test
    void testEquals_UserDtosShouldReturnFalseWhenTheyAreNotEqual() {
        UserDto userDto1 = UserDto.builder().username("user1").role(Roles.USER).build();
        UserDto userDto2 = UserDto.builder().username("user2").role(Roles.USER).build();

        boolean result = userDto1.equals(userDto2);

        assertFalse(result, "should not be equal");
    }

    @Test
    void testEqualsNullObjectShouldReturnFalse() {
        UserDto userDto = UserDto.builder().username("user").role(Roles.USER).build();
        boolean result = userDto.equals(null);
        assertFalse(result, "UserDto should not be null");
    }

    @Test
    void testCanEqualShouldReturnTrue() {
        UserDto userDto1 = UserDto.builder().username("user").role(Roles.USER).build();
        UserDto userDto2 = UserDto.builder().username("user").role(Roles.USER).build();

        boolean result = userDto1.canEqual(userDto2);

        assertTrue(result, "Should be equal");
    }

    @Test
    void testHashCode_IdenticalUserDtosShouldReturnEqualHashCodes() {
        UserDto userDto1 = UserDto.builder().username("user").role(Roles.USER).build();
        UserDto userDto2 = UserDto.builder().username("user").role(Roles.USER).build();

        int hashCode1 = userDto1.hashCode();
        int hashCode2 = userDto2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal");
    }

    @Test
    void testHashCode_DifferentUserDtosShouldReturnDifferentHashCodes() {
        UserDto userDto1 = UserDto.builder().username("user1").role(Roles.USER).build();
        UserDto userDto2 = UserDto.builder().username("user2").role(Roles.USER).build();

        int hashCode1 = userDto1.hashCode();
        int hashCode2 = userDto2.hashCode();

        assertNotEquals(hashCode1, hashCode2, "Hash codes should not be equal");
    }
}
