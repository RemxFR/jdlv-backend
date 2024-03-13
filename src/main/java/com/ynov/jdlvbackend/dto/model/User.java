package com.ynov.jdlvbackend.dto.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User.
 */
@Getter
@Setter
@Data
@Entity
@Table(name = "t_user")
public class User {
    /**
     * Id + clé primaire dans la table t_user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Login + colomne login dans la table t_user.
     */
    @Column(name = "login", unique = true)
    private String login;
    /**
     * Mot de passe + colomne mdp dans la table t_user.
     */
    @Column(name = "mdp")
    private String mdp;
    /**
     * Liste de ReglesCustoms + cardinalité OneToMany avec la table t_reglesCustoms.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReglesCustom> reglesCustoms;

    /**
     * Constrcteur vide.
     */
    public User() {
    }

    /**
     * Constructeur sans la liste de reglesCustom.
     *
     * @param login
     * @param mdp
     */
    public User(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }
}
