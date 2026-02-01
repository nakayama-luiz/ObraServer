package com.diario.obra.service;

import com.diario.obra.domain.Obra;
import com.diario.obra.repository.ObraRepository;
import com.diario.obra.utils.RsqlSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    public Obra save(Obra obra) {
        return obraRepository.save(obra);
    }

    public Obra findById(UUID id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada para o id: " + id));
    }

    public List<Obra> findAll() {
        return obraRepository.findAll();
    }

    public Obra update(UUID id, Obra obraAtualizada) {
        Obra obraExistente = findById(id);

        obraExistente.setNome(obraAtualizada.getNome());
        obraExistente.setLogradouro(obraAtualizada.getLogradouro());
        obraExistente.setNumero(obraAtualizada.getNumero());
        obraExistente.setBairro(obraAtualizada.getBairro());
        obraExistente.setCidade(obraAtualizada.getCidade());
        obraExistente.setEstado(obraAtualizada.getEstado());
        obraExistente.setPais(obraAtualizada.getPais());
        obraExistente.setLatitude(obraAtualizada.getLatitude());
        obraExistente.setLongitude(obraAtualizada.getLongitude());
        obraExistente.setCno(obraAtualizada.getCno());
        obraExistente.setAlvara(obraAtualizada.getAlvara());
        obraExistente.setDataInicio(obraAtualizada.getDataInicio());
        obraExistente.setQtdFuncionarios(obraAtualizada.getQtdFuncionarios());

        return obraRepository.save(obraExistente);
    }

    public void deletar(UUID id) {
        Obra obra = findById(id);
        obraRepository.delete(obra);
    }

    public Page<Obra> findByRsql(String search, Pageable pageable) {

        Specification<Obra> specification = null;

        if (search != null && !search.isBlank()) {
            specification = RsqlSpecificationBuilder.build(search);
        }

        if (Objects.isNull(specification)) {
            return obraRepository.findAll(pageable);
        }

        return obraRepository.findAll(specification, pageable);
    }

    public Integer findTotalFuncionarioByObra(UUID obraId) {
        return obraRepository.findTotalFuncionarioByObra(obraId);
    }
}
