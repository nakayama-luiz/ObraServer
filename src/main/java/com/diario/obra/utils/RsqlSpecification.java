package com.diario.obra.utils;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;

public class RsqlSpecification<T> implements Specification<T> {

    private final ComparisonNode node;

    public RsqlSpecification(ComparisonNode node) {
        this.node = node;
    }

    @Override
    public Predicate toPredicate(
            Root<T> root,
            CriteriaQuery<?> query,
            CriteriaBuilder cb) {

        String field = node.getSelector();
        String value = node.getArguments().get(0);

        return switch (node.getOperator().getSymbol()) {
            case "==" -> cb.like(
                    cb.lower(root.get(field).as(String.class)),
                    value.toLowerCase().replace("*", "%")
            );
            case "!=" -> cb.notEqual(root.get(field), value);
            default -> throw new UnsupportedOperationException(
                    "Operador não suportado: " + node.getOperator()
            );
        };
    }
}
