package com.diario.obra.service;

import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.repository.EmpresaObraRepository;
import com.diario.obra.utils.RsqlSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmpresaObraService {

    private final EmpresaObraRepository repository;

    public EmpresaObra save(EmpresaObra obra) {
        return repository.save(obra);
    }

    public EmpresaObra findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmpresaObra não encontrada para o id: " + id));
    }

    public List<EmpresaObra> findAll() {
        return repository.findAll();
    }

    public void deletar(UUID id) {
        EmpresaObra obra = findById(id);
        repository.delete(obra);
    }

    public Page<EmpresaObra> findByRsql(String search, Pageable pageable) {

        Specification<EmpresaObra> specification = null;

        if (search != null && !search.isBlank()) {
            specification = RsqlSpecificationBuilder.build(search);
        }

        if(Objects.isNull(specification)) {
            return repository.findAll(pageable);
        }

        return repository.findAll(specification, pageable);
    }
}
