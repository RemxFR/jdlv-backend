package com.ynov.jdlvbackend.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", unique = true)
    private String login;
    private String mdp;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReglesCustom> reglesCustoms;

    public User() {
    }
    public User(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }
}
