package com.diario.obra.service;

import com.diario.obra.domain.Empresa;
import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.domain.Obra;
import com.diario.obra.repository.EmpresaObraRepository;
import com.diario.obra.utils.RsqlSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        if (Objects.isNull(specification)) {
            return repository.findAll(pageable);
        }

        return repository.findAll(specification, pageable);
    }

    public EmpresaObra relate(UUID obraId, UUID empresaId) {
        return repository.save(new EmpresaObra(obraId, empresaId));
    }

    @Transactional
    public void unrelate(UUID obraId, UUID empresaId) {
        repository.deleteByObraAndEmpresa(Obra.of(obraId), Empresa.of(empresaId));
    }

    public Empresa findByObraId(UUID obraId) {
        EmpresaObra empresaObra = repository.findByObraId(obraId);
        return Optional.ofNullable(empresaObra.getEmpresa())
                .orElse(new Empresa());
    }

    public EmpresaObra findOneByObraAndEmpresaId(UUID obraId) {
        return repository.findByObraId(obraId);
    }
}
