package com.example.mercadonabackend.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationDtoTest {

    @Test
    public void testConstructorAndGetters() {
        // Given
        Long id = 1L;
        String username = "john.doe";
        String password = "password";
        String email = "john.doe@example.com";

        // When
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(id);
        registrationDto.setEmail(email);
        registrationDto.setPassword(password);
        registrationDto.setUsername(username);

        // Then
        assertNotNull(registrationDto);
        assertEquals(id, registrationDto.getId());
        assertEquals(username, registrationDto.getUsername());
        assertEquals(password, registrationDto.getPassword());
        assertEquals(email, registrationDto.getEmail());
    }

    @Test
    public void testEquals() {
        RegistrationDto registrationDto1 = new RegistrationDto(1L, "username", "password", "email");
        RegistrationDto registrationDto2 = new RegistrationDto(1L, "username", "password", "email");
        RegistrationDto registrationDto3 = new RegistrationDto(2L, "username2", "password2", "email2");

        // Vérifie que l'égalité est correctement vérifiée
        assertTrue(registrationDto1.equals(registrationDto2));
        assertFalse(registrationDto1.equals(registrationDto3));

        // Vérifie que la réflexivité est respectée (x.equals(x) doit être true)
        assertTrue(registrationDto1.equals(registrationDto1));

        // Vérifie que la symétrie est respectée (x.equals(y) doit être équivalent à y.equals(x))
        assertTrue(registrationDto2.equals(registrationDto1));

        // Vérifie que la transitivité est respectée (x.equals(y) et y.equals(z) implique x.equals(z))
        RegistrationDto registrationDto4 = new RegistrationDto(1L, "username", "password", "email");
        assertTrue(registrationDto1.equals(registrationDto2));
        assertTrue(registrationDto2.equals(registrationDto4));
        assertTrue(registrationDto1.equals(registrationDto4));

        // Vérifie que l'égalité avec null est correctement vérifiée
        assertFalse(registrationDto1.equals(null));
    }

}
