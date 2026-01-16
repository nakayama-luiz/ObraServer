package com.diario.obra.resource;

import com.diario.obra.domain.EmpresaObraFuncionario;
import com.diario.obra.service.EmpresaObraService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/empresa-obra-funcionario")
@AllArgsConstructor
public class EmpresaObraFuncionarioResource extends CrudResource<EmpresaObraFuncionario, UUID> {

    private final EmpresaObraService service;

}
