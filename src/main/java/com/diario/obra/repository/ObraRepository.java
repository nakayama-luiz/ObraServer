package com.diario.obra.repository;

import com.diario.obra.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ObraRepository extends JpaRepository<Obra, UUID>, JpaSpecificationExecutor<Obra> {

    @Query("""
            select count(f.id) from Obra o
            inner join EmpresaObra eo on eo.obra = o
            inner join EmpresaObraFuncionario eof on eof.empresaObra = eo
            inner join eof.funcionario f
            where o.id = :obraId
            """)
    Integer findTotalFuncionarioByObra(UUID obraId);
}
