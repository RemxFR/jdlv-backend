package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.UserDto;
import com.ynov.jdlvbackend.dto.repository.IUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceTests est une classe pour tester ")
public class UserServiceTests {

    @Mock
    private IUserRepo iUserRepo;
    private UserService userService;

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(new UserDto("login", "mdp")));
    }

    @BeforeEach
    public void setup() {
        this.userService = new UserService(iUserRepo);
    }

    @DisplayName("Que l'instance de UserService n'est pas nulle.")
    @Test
    public void testerInstanceDeUserService() {
        assertTrue(this.userService != null);
    }

    @DisplayName("Que si un user est passé en paramètre, la méthode renverra un user après l'enregistrement en bdd.")
    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void testerSauvegarderUser(UserDto userDto) {
        assertTrue(this.userService.saveUser(userDto) != null);
    }

    @DisplayName("Que si un user est passé en paramètre, la méthode renverra le bon objet instancié, " +
            "avec les même données après la persistance en bdd.")
    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void testerLoginDuUserEnregistre(UserDto userDto) {
        assertTrue(this.userService.saveUser(userDto).getLogin().equals("login"));
    }
}
