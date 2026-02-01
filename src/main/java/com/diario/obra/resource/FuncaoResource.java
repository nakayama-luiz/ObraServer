package com.diario.obra.resource;

import com.diario.obra.domain.Funcao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/funcao")
@AllArgsConstructor
public class FuncaoResource extends CrudResource<Funcao, UUID> {

}
