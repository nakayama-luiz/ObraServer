package com.diario.obra.repository;

import com.diario.obra.domain.Funcao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncaoRepository extends CrudRepository<Funcao, UUID> {

}
