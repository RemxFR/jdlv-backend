package com.ynov.jdlvbackend.dto.repository;

import com.ynov.jdlvbackend.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface repository pour gérer les opérations CRUD sur l'objet User.
 */
@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    /**
     * Query pour trouver un utilisateur par son login.
     * @param userLogin
     * @return
     */
    @Query("Select u From User u WHERE u.login = ?1")
    Optional<User> findByLogin(String userLogin);
}
