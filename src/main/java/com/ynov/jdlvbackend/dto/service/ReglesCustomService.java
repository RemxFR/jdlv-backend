package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.ReglesCustom;
import com.ynov.jdlvbackend.dto.model.ReglesCustomDto;
import com.ynov.jdlvbackend.dto.model.User;
import com.ynov.jdlvbackend.dto.repository.IReglesCustomRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe qui permet de gérer les opérations CRUD et logique sur l'objet ReglesCustom.
 */
@Service
@AllArgsConstructor
public class ReglesCustomService {
    /**
     * Interface repository pour gérer les opérations CRUD sur l'objet ReglesCustom.
     */
    @Autowired
    IReglesCustomRepo iReglesCustomRepo;
    /**
     * Service pour gérer les opérations CRUD et logiques sur l'objet Utilisateur.
     */
    @Autowired
    UserService userService;
    /**
     * Méthode pour sauvegarder les règles customisées par l'utilisateur et lui affilier par rapport à son id.
     * @param reglesCustom
     * @param loginUser
     * @return
     */
    public ReglesCustom sauverRegles(ReglesCustomDto reglesCustom, String loginUser) {
        ReglesCustom regCust = null;
        User user = null;
        if (loginUser != null && !loginUser.equals("") && reglesCustom != null) {
            user = this.userService.findUserByLogin(loginUser);
            ReglesCustom custom = ReglesCustom.builder()
                    .reproduction(reglesCustom.getReproduction())
                    .surPopulation(reglesCustom.getSurPopulation())
                    .sousPopulation(reglesCustom.getSousPopulation())
                    .tailleGrille(reglesCustom.getTailleGrille())
                    .user(user)
                    .build();
            regCust = this.iReglesCustomRepo.save(custom);
        }
        return regCust;
    }
    /**
     * Méthode pour récupére les règles enregistrées par l'utilisateur à partir de son login.
     * @param login
     * @return
     */
    public List<ReglesCustom> recupererRegles(String login) {
        List<ReglesCustom> reglesCustoms = null;
        User user = null;
        if (login != null && !login.equals("")) {
            user = this.userService.findUserByLogin(login);
            if (user != null) {
                Optional<List<ReglesCustom>> listReglesOpt = this.iReglesCustomRepo.recupererReglesParIdUser(user.getId());
                if (listReglesOpt.isPresent()) {
                    reglesCustoms = listReglesOpt.get();
                }
            }
        }
        return reglesCustoms;
    }
}
