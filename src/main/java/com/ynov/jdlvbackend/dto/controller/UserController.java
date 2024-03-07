package com.ynov.jdlvbackend.dto.controller;

import com.ynov.jdlvbackend.dto.model.UserDto;
import com.ynov.jdlvbackend.dto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur qui gère la réception des requêtes Http envoyées du front en ce qui concerne l'objet User.
 */
@RestController
@RequestMapping("connexion")
@AllArgsConstructor
public class UserController {
    /**
     * Service qui gère les opérations CRUD et logique sur l'objet User.
     */
    @Autowired
    private UserService userService;

    /**
     * Endpoint relative à la méthode d'inscription d'un utilisateur.
      * @param userDto
     * @return
     */
    @PostMapping("inscription")
    public ResponseEntity<UserDto> inscrireUser(@RequestBody UserDto userDto) {
        UserDto userInscrit = null;
        if (userDto != null) {
            userInscrit = this.userService.saveUser(userDto);
        }
        return new ResponseEntity<>(userInscrit, HttpStatus.OK);
    }
    /**
     * Méthode relative à l'authentification d'un utilisateur au moment de sa connexion.
     * @param userDto
     * @return
     */
    @PostMapping("auth")
    public ResponseEntity<UserDto> inscriptionString(@RequestBody UserDto userDto) {
        if (userDto != null) {
            UserDto userDtoConnecte = this.userService.seConnecter(userDto);
            if (userDtoConnecte != null) {
                return new ResponseEntity<>(userDtoConnecte, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
