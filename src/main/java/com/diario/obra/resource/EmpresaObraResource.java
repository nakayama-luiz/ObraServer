package com.diario.obra.resource;

import com.diario.obra.domain.Empresa;
import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.service.EmpresaObraService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/empresa-obra")
@AllArgsConstructor
public class EmpresaObraResource {

    private final EmpresaObraService service;

    @PostMapping
    public ResponseEntity<EmpresaObra> criar(@RequestBody EmpresaObra obra) {
        EmpresaObra criada = service.save(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaObra> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaObra>> search(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        Page<EmpresaObra> page = service.findByRsql(search, pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }

    @PostMapping("/obra/{obraId}/empresa/{empresaId}")
    public ResponseEntity<EmpresaObra> relate(@PathVariable UUID obraId, @PathVariable UUID empresaId) {
        return ResponseEntity.ok(service.relate(obraId, empresaId));
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<Empresa> findEmpresaByObra(@PathVariable UUID obraId) {
        return ResponseEntity.ok(service.findByObraId(obraId));
    }

    @DeleteMapping("/obra/{obraId}/empresa/{empresaId}")
    public ResponseEntity<Void> unrelate(@PathVariable UUID obraId, @PathVariable UUID empresaId) {
        service.unrelate(obraId, empresaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-relation")
    public ResponseEntity<EmpresaObra> findByObraAndEmpresaId(
            @RequestParam(value = "obraId") UUID obraId) {

       return ResponseEntity.ofNullable(service.findOneByObraAndEmpresaId(obraId));
    }

}
