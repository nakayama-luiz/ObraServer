package com.diario.obra.service;

import com.diario.obra.domain.Funcionario;
import com.diario.obra.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FuncionarioService extends CrudService<Funcionario, UUID> {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario update(UUID id, Funcionario updated) {
        Funcionario existingFuncionario = findById(id);

        existingFuncionario.setNome(updated.getNome());
        existingFuncionario.setCpf(updated.getCpf());
        existingFuncionario.setTelefone(updated.getTelefone());
        existingFuncionario.setEmpresa(updated.getEmpresa());
        existingFuncionario.setFuncao(updated.getFuncao());
        existingFuncionario.setEndereco(updated.getEndereco());

        return funcionarioRepository.save(existingFuncionario);
    }
}