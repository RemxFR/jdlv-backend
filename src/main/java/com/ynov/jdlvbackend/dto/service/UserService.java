package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.ReglesCustom;
import com.ynov.jdlvbackend.dto.model.ReglesCustomDto;
import com.ynov.jdlvbackend.dto.model.User;
import com.ynov.jdlvbackend.dto.model.UserDto;
import com.ynov.jdlvbackend.dto.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe qui permet de gérer les service relative aux opérations CRUD et logiques sur l'objet User.
 */
@Service
@AllArgsConstructor
public class UserService {
    /**
     * Interface repository pour gérer les opération CRUD sur l'objet User.
     */
    @Autowired
    private IUserRepo iUserRepo;

    /**
     * Méthode pour sauver un User depuis un UserDto
     *
     * @param userDto
     * @return
     */
    public UserDto saveUser(UserDto userDto) {
        User existingLogin = this.findUserByLogin(userDto.getLogin());
        if (existingLogin == null) {
            User user = new User(userDto.getLogin(), userDto.getMdp());
            this.iUserRepo.save(user);
            return userDto;
        }
        return null;
    }

    /**
     * Méthode d'authentification de l'utilisateur afin de permettre la connexion de l'utilisateur au jeu.
     *
     * @param userDto
     * @return
     */
    public UserDto seConnecter(UserDto userDto) {
        User existingUser = this.findUserByLogin(userDto.getLogin());
        if (existingUser != null && existingUser.getMdp().equals(userDto.getMdp())) {
            List<ReglesCustomDto> reglesCustomDtoList = new ArrayList<>();
            for (ReglesCustom reglesCustom : existingUser.getReglesCustoms()) {
                ReglesCustomDto reglesCustomDto = ReglesCustomDto.builder()
                        .surPopulation(reglesCustom.getSurPopulation())
                        .sousPopulation(reglesCustom.getSousPopulation())
                        .reproduction(reglesCustom.getReproduction())
                        .tailleGrille(reglesCustom.getTailleGrille())
                        .build();
                reglesCustomDtoList.add(reglesCustomDto);
            }
            UserDto userDtoTrouve = UserDto.builder()
                    .login(existingUser.getLogin())
                    .reglesCustomList(reglesCustomDtoList)
                    .build();
            return userDtoTrouve;
        }
        return null;
    }

    /**
     * Méthode pour trouver un Utilisateur par son login.
     *
     * @param userLogin
     * @return
     */
    public User findUserByLogin(String userLogin) {
        Optional<User> userOpt = this.iUserRepo.findByLogin(userLogin);
        return userOpt.orElse(null);
    }
}
