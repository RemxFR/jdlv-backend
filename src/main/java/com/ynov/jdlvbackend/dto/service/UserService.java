package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.User;
import com.ynov.jdlvbackend.dto.model.UserDto;
import com.ynov.jdlvbackend.dto.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private IUserRepo iUserRepo;

    public UserDto saveUser(UserDto userDto) {
        User existingLogin = this.findUserByLogin(userDto.getLogin());
        if (existingLogin == null) {
            User user = new User(userDto.getLogin(), userDto.getMdp());
            this.iUserRepo.save(user);
            return userDto;
        }
        return null;
    }

    public UserDto seConnecter(UserDto userDto) {
        User existingUser = this.findUserByLogin(userDto.getLogin());
        if (existingUser != null && existingUser.getMdp().equals(userDto.getMdp())) {
            UserDto userDtoTrouve = UserDto.builder().login(existingUser.getLogin()).mdp("").build();
            return userDtoTrouve;
        }
        return null;
    }

    public User findUserByLogin(String userLogin) {
        Optional<User> userOpt = this.iUserRepo.findByLogin(userLogin);
        return userOpt.orElse(null);
    }
}
