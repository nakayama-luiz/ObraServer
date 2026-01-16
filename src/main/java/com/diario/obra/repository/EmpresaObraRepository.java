package com.diario.obra.repository;

import com.diario.obra.domain.EmpresaObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaObraRepository extends JpaRepository<EmpresaObra, UUID>, JpaSpecificationExecutor<EmpresaObra> {

}
