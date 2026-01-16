package com.diario.obra.service;

import com.diario.obra.repository.CrudRepository;
import com.diario.obra.utils.RsqlSpecificationBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@Slf4j
@Getter
public class CrudService<T, R> {

    @Autowired
    private CrudRepository<T, R> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(R id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada para o id: " + id));
    }

    public Page<T> findByRsql(String search, Pageable pageable) {

        Specification<T> specification = null;

        if (search != null && !search.isBlank()) {
            specification = RsqlSpecificationBuilder.build(search);
        }

        if (Objects.isNull(specification)) {
            return repository.findAll(pageable);
        }

        return repository.findAll(specification, pageable);
    }

    public void deletar(R id) {
        T obra = findById(id);

        repository.delete(obra);
    }

    public T update(R id, T updated) {

        log.info("Não implementado " + id);

        return repository.save(updated);
    }
}
