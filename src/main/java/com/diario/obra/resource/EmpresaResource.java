package com.diario.obra.resource;

import com.diario.obra.domain.Empresa;
import com.diario.obra.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/empresa")
@AllArgsConstructor
public class EmpresaResource {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa obra) {
        Empresa criada = empresaService.save(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Empresa>> search(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        Page<Empresa> page = empresaService.findByRsql(search, pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(
            @PathVariable UUID id,
            @RequestBody Empresa obra
    ) {
        Empresa atualizada = empresaService.update(id, obra);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        empresaService.deletar(id);
    }
}
