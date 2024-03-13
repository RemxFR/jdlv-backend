package com.ynov.jdlvbackend.dto.repository;

import com.ynov.jdlvbackend.dto.model.Chiffrage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChiffrage extends JpaRepository<Chiffrage, Integer> {
}
