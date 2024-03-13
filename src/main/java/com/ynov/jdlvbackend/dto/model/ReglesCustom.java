package com.ynov.jdlvbackend.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * ReglesCustom
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "t_reglesCustom")
@Builder
public class ReglesCustom {
    /**
     * Id + clé primaire de la table t_reglesCustom.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Sur-population + colonne surPopulation dans la table t_reglesCustom.
     */
    private int surPopulation;
    /**
     * Sous-population + colonne sousPopulation dans la table t_reglesCustom.
     */
    private int sousPopulation;
    /**
     * Reproduction + colonne Reproduction dans la table t_reglesCustom.
     */
    private int reproduction;
    /**
     * Sur-population + colonne surPopulation dans la table t_reglesCustom.
     */
    private int tailleGrille;
    /**
     * User + relation de cardinalité avec t_user + clé étrangère user_id dans la table t_reglesCustom.
     */
    @JsonIgnore
    @ManyToOne
    private User user;
}


