package com.diario.obra.resource;

import com.diario.obra.domain.Funcionario;
import com.diario.obra.service.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/funcionario")
@AllArgsConstructor
public class FuncionarioResource extends CrudResource<Funcionario, UUID> {

    private final FuncionarioService funcionarioService;

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(
            @PathVariable UUID id,
            @RequestBody Funcionario funcionario
    ) {
        return ResponseEntity.ok(funcionarioService.update(id, funcionario));
    }
}