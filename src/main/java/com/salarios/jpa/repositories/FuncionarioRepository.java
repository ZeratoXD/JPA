package com.salarios.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salarios.jpa.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository <Funcionario, UUID> {

}
