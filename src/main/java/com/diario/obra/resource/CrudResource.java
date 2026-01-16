package com.diario.obra.resource;

import com.diario.obra.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CrudResource<T, R> {

    @Autowired
    private CrudService<T, R> service;

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T entity) {
        T create = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping
    public ResponseEntity<Page<T>> search(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        Page<T> page = service.findByRsql(search, pageable);
        return ResponseEntity.ok(page);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable R id) {
        service.deletar(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable R id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
