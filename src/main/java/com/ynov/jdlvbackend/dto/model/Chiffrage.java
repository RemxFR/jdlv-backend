package com.ynov.jdlvbackend.dto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_cle")
@Getter
@Setter
@Builder
public class Chiffrage {
    @Id
    private int id;
    @Column(name = "cle_privee", columnDefinition = "BIGINT")
    private byte[] clePrivee;
    @Column(name = "cle_publique", columnDefinition = "BIGINT")
    private byte[] clePublique;
}
