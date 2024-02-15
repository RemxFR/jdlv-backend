package com.ynov.jdlvbackend.persistence;

import com.ynov.jdlvbackend.persistence.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private IUserRepo iUserRepo;

    public User saveUser(User user) {
        User existingLogin = this.findUser(user);
        if (existingLogin == null) {
            return this.iUserRepo.save(user);
        }
        return null;
    }

    public User seConnecter(User user) {
        User existingUser = this.findUser(user);
        if (existingUser != null && existingUser.getMdp().equals(user.getMdp())) {
            return existingUser;
        }
        return null;
    }

    public User findUser(User user) {
        Optional<User> userOpt = this.iUserRepo.findByLogin(user.getLogin());
        return userOpt.orElse(null);
    }
}
