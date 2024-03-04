package com.ynov.jdlvbackend.dto.controller;

import com.ynov.jdlvbackend.dto.model.UserDto;
import com.ynov.jdlvbackend.dto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("connexion")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("infos")
    public ResponseEntity<String> getInfos() {
        return new ResponseEntity<>("Connexion OK!", HttpStatus.OK);
    }

    @PostMapping("inscription")
    public ResponseEntity<UserDto> inscrireUser(@RequestBody UserDto userDto) {
        UserDto userInscrit = null;
        if (userDto != null) {
            userInscrit = this.userService.saveUser(userDto);
        }
        return new ResponseEntity<>(userInscrit, HttpStatus.OK);
    }

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
