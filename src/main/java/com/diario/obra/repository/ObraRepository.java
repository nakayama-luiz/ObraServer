package com.diario.obra.repository;

import com.diario.obra.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ObraRepository extends JpaRepository<Obra, UUID>, JpaSpecificationExecutor<Obra> {

}
