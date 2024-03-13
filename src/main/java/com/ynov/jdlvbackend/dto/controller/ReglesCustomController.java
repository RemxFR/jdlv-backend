package com.ynov.jdlvbackend.dto.controller;

import com.ynov.jdlvbackend.dto.model.ReglesCustom;
import com.ynov.jdlvbackend.dto.model.ReglesCustomDto;
import com.ynov.jdlvbackend.dto.service.ReglesCustomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur qui gère la réception des requêtes Http relatives à l'objet ReglesCustom.
 */
@RestController
@RequestMapping("regles")
@AllArgsConstructor
public class ReglesCustomController {
    /**
     * Service qui gère es opérations CRUD et logique sur l'objet ReglesCustom.
     */
    @Autowired
    private ReglesCustomService reglesCustomService;

    /**
     * Endpoint relatif à la sauvegarde de règles dans un profil utilisateur à partir de son profil.
     *
     * @param reglesCustomDto
     * @param login
     * @return
     */
    @PostMapping("sauver/{login}")
    public ResponseEntity<ReglesCustom> sauverRegles(@RequestBody ReglesCustomDto reglesCustomDto,
                                                     @PathVariable("login") String login) {
        ReglesCustom customRegles = this.reglesCustomService.sauverRegles(reglesCustomDto, login);
        return new ResponseEntity<>(customRegles, HttpStatus.OK);
    }

    /**
     * Endpoint relatif à la récupération des règles enregistrées par un utilisateur, à partir de son login.
     *
     * @param login
     * @return
     */
    @GetMapping("recuperer/{login}")
    public ResponseEntity<List<ReglesCustomDto>> recupererRegles(@PathVariable("login") String login) {
        List<ReglesCustomDto> reglesCustoms = this.reglesCustomService.recupererRegles(login);
        return new ResponseEntity<>(reglesCustoms, HttpStatus.OK);
    }
}
