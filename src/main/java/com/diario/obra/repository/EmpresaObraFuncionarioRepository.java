package com.diario.obra.repository;

import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.domain.EmpresaObraFuncionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaObraFuncionarioRepository extends CrudRepository<EmpresaObraFuncionario, UUID> {

    @Query("select a from EmpresaObraFuncionario a where a.empresaObra = :empresaObra")
    List<EmpresaObraFuncionario> findAllByEmpresaObraFuncionario(EmpresaObra empresaObra);
}
