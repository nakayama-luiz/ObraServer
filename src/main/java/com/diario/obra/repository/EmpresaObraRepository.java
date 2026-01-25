package com.diario.obra.repository;

import com.diario.obra.domain.Empresa;
import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaObraRepository extends JpaRepository<EmpresaObra, UUID>, JpaSpecificationExecutor<EmpresaObra> {

    EmpresaObra findByObraId(UUID obraId);

    @Query("delete from EmpresaObra where empresa.id = :empresaId and obra.id = :obraId ")
    void deletByObraAndEmpresa(UUID obraId, UUID empresaId);

    void deleteByObraAndEmpresa(Obra obra, Empresa empresa);
}
