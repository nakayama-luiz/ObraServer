package com.diario.obra.repository;

import com.diario.obra.domain.EmpresaObraFuncionario;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaObraFuncionarioRepository extends CrudRepository<EmpresaObraFuncionario, UUID> {
    
}
