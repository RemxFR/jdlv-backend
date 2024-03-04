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

@RestController
@RequestMapping("regles")
@AllArgsConstructor
public class ReglesCustomController {

    @Autowired
    private ReglesCustomService reglesCustomService;

    @PostMapping("sauver/{login}")
    public ResponseEntity<ReglesCustom> sauverRegles(@RequestBody ReglesCustomDto reglesCustomDto,
                                                     @PathVariable("login") String login) {
        ReglesCustom customRegles = this.reglesCustomService.sauverRegles(reglesCustomDto, login);
        return new ResponseEntity<>(customRegles, HttpStatus.OK);
    }

    @GetMapping("recuperer/{login}")
    public ResponseEntity<List<ReglesCustom>> recupererRegles(@PathVariable("login") String login) {
        List<ReglesCustom> reglesCustoms = this.reglesCustomService.recupererRegles(login);
        return new ResponseEntity<>(reglesCustoms, HttpStatus.OK);
    }
}
