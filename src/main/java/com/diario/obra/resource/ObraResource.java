package com.diario.obra.resource;

import com.diario.obra.domain.Obra;
import com.diario.obra.service.ObraService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/obra")
@AllArgsConstructor
public class ObraResource {

    private final ObraService obraService;

    @PostMapping
    public ResponseEntity<Obra> criar(@RequestBody Obra obra) {
        Obra criada = obraService.save(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(obraService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Obra>> search(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        Page<Obra> page = obraService.findByRsql(search, pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizar(
            @PathVariable UUID id,
            @RequestBody Obra obra
    ) {
        Obra atualizada = obraService.update(id, obra);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        obraService.deletar(id);
    }

    @GetMapping("/funcionario/total/{obraId}")
    public ResponseEntity<Integer> findTotalFuncionarioByObra(@PathVariable String obraId) {
        return ResponseEntity.ofNullable(obraService.findTotalFuncionarioByObra(UUID.fromString(obraId)));
    }
}
