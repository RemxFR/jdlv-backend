package com.ynov.jdlvbackend.persistence;

import com.ynov.jdlvbackend.persistence.model.User;
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
    public ResponseEntity<User> inscrireUser(@RequestBody User user) {
        System.out.println("user send: " + user.getLogin());
        User userInscrit = null;
        if (user != null) {
            userInscrit = this.userService.saveUser(user);
        }
        return new ResponseEntity<>(userInscrit, HttpStatus.OK);
    }

    @PostMapping("auth")
    public ResponseEntity<User> inscriptionString(@RequestBody User user) {
        if(user != null) {
            User userConnecte = this.userService.seConnecter(user);
            if(userConnecte != null) {
                return new ResponseEntity<>(userConnecte, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
