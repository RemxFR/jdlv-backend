package com.ynov.jdlvbackend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReglesCustomDto {
    private int surPopulation;
    private int sousPopulation;
    private int reproduction;
    private int tailleGrille;

}
