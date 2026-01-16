package com.diario.obra.service;

import com.diario.obra.domain.Empresa;
import com.diario.obra.repository.EmpresaRepository;
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
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public Empresa save(Empresa obra) {
        return empresaRepository.save(obra);
    }

    public Empresa findById(UUID id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada para o id: " + id));
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa update(UUID id, Empresa updated) {
        Empresa empresaExistente = findById(id);

        empresaExistente.setCnpj(updated.getCnpj());
        empresaExistente.setEndereco(updated.getEndereco());
        empresaExistente.setRazaoSocial(updated.getRazaoSocial());
        empresaExistente.setTelefone(updated.getTelefone());

        return empresaRepository.save(empresaExistente);
    }

    public void deletar(UUID id) {
        Empresa obra = findById(id);
        empresaRepository.delete(obra);
    }

    public Page<Empresa> findByRsql(String search, Pageable pageable) {

        Specification<Empresa> specification = null;

        if (search != null && !search.isBlank()) {
            specification = RsqlSpecificationBuilder.build(search);
        }

        if(Objects.isNull(specification)) {
            return empresaRepository.findAll(pageable);
        }

        return empresaRepository.findAll(specification, pageable);
    }
}
