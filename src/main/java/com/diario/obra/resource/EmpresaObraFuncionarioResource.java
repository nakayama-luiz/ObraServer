package com.diario.obra.resource;

import com.diario.obra.domain.EmpresaObraFuncionario;
import com.diario.obra.service.EmpresaObraFuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/empresa-obra-funcionario")
@AllArgsConstructor
public class EmpresaObraFuncionarioResource extends CrudResource<EmpresaObraFuncionario, UUID> {

    private final EmpresaObraFuncionarioService service;

    @GetMapping("/search-obra")
    ResponseEntity<List<EmpresaObraFuncionario>> findObraById(@RequestParam("obraId") String obraId) {
        return ResponseEntity.ok(service.findAllByObra(UUID.fromString(obraId)));
    }

}
