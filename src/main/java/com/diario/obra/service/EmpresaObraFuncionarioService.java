package com.diario.obra.service;

import com.diario.obra.domain.EmpresaObra;
import com.diario.obra.domain.EmpresaObraFuncionario;
import com.diario.obra.repository.EmpresaObraFuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmpresaObraFuncionarioService extends CrudService<EmpresaObraFuncionario, UUID> {

    private final EmpresaObraService empresaObraService;
    private final EmpresaObraFuncionarioRepository repository;

    public List<EmpresaObraFuncionario> findAllByObra(UUID obraId) {

        EmpresaObra empresaObra = empresaObraService.findOneByObraAndEmpresaId(obraId);

        return repository.findAllByEmpresaObraFuncionario(empresaObra);
    }

}
