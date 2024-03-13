package com.ynov.jdlvbackend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    /**
     * Liste des règles enregistrées par le joueur.
     */
    private List<ReglesCustomDto> reglesCustomList;
}
