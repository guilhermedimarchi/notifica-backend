package com.notifica.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notifica.core.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
