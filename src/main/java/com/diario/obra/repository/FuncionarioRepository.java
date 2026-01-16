package com.diario.obra.repository;

import com.diario.obra.domain.Funcionario;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, UUID> {

}