package com.ynov.jdlvbackend.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "t_reglesCustom")
public class ReglesCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int surPopulation;
    private int sousPopulation;
    private int reproduction;
    private int tailleGrille;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}


