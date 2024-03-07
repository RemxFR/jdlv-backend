package com.ynov.jdlvbackend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * UserDto.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    /**
     * Login.
     */
    private String login;
    /**
     * Mot de passe.
     */
    private String mdp;
}
