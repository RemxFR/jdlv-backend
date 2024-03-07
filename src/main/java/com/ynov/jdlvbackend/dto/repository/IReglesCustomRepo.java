package com.ynov.jdlvbackend.dto.repository;

import com.ynov.jdlvbackend.dto.model.ReglesCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository qui gère les opérations CRUD pour l'objet ReglesCustom.
 */
@Repository
public interface IReglesCustomRepo extends JpaRepository<ReglesCustom, Integer> {
    /**
     * Query qui permet de retrouver les règles par le login de l'utilisateur qui les a enregistrées.
     * @param idUSer
     * @return
     */
    @Query("Select r From ReglesCustom r Where r.user.id = ?1")
    Optional<List<ReglesCustom>> recupererReglesParIdUser(int idUSer);
}
