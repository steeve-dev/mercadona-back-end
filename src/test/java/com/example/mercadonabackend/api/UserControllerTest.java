package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.UserService;
import com.example.mercadonabackend.Service.impl.UserServiceImpl;
import com.example.mercadonabackend.api.auth.UserController;
import com.example.mercadonabackend.dto.RegistrationDto;
import com.example.mercadonabackend.pojo.Role;
import com.example.mercadonabackend.pojo.UserEntity;
import com.example.mercadonabackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;


    @Test
    public void testLoginPage() {
        String page = userController.loginPage();
        assertEquals("login", page);
    }


    @Test
    public void testLogout() {
        String page = userController.logout();
        assertEquals("index", page);
    }

    @Test
    public void testCreateUser() {
        // Création d'un objet RegistrationDto
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("john.doe");
        registrationDto.setEmail("john.doe@example.com");
        registrationDto.setPassword("password123");

        // Configuration du mock PasswordEncoder
        when(passwordEncoder.encode(registrationDto.getPassword()))
                .thenReturn("hashedPassword");

        // Appel de la méthode createUser
        userService.createUser(registrationDto);

        // Vérification que userRepository.save a été appelé avec l'utilisateur créé
        ArgumentCaptor<UserEntity> argument = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(argument.capture());
        UserEntity savedUser = argument.getValue();
        assertEquals(registrationDto.getUsername(), savedUser.getUsername());
        assertEquals(registrationDto.getEmail(), savedUser.getEmail());
        assertEquals("hashedPassword", savedUser.getPassword());
        assertEquals(Role.ADMIN, savedUser.getRole());
    }

}
