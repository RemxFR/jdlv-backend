package com.ynov.jdlvbackend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ReglesCustomDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
