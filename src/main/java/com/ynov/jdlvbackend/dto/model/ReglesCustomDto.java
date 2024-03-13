package com.ynov.jdlvbackend.dto.model;

import lombok.*;

/**
 * ReglesCustomDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReglesCustomDto {
    /**
     * Sur-population.
     */
    private int surPopulation;
    /**
     * Sous-population.
     */
    private int sousPopulation;
    /**
     * Reproduction.
     */
    private int reproduction;
    /**
     * Taille grille.
     */
    private int tailleGrille;

}
