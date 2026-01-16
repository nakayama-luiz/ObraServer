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
        String value = node.getArguments().getFirst();

        Path<String> path = getPath(root, field);

        return switch (node.getOperator().getSymbol()) {
            case "==" -> cb.like(
                    cb.lower(path.as(String.class)),
                    value.toLowerCase().replace("*", "%")
            );
            case "!=" -> cb.notEqual(path, value);
            default -> throw new UnsupportedOperationException(
                    "Operador não suportado: " + node.getOperator()
            );
        };
    }

    private Path<String> getPath(Root<?> root, String field) {
        if (field.contains(".")) {
            String[] parts = field.split("\\.");
            Path<?> path = root;

            for (String part : parts) {
                path = path.get(part);
            }

            return (Path<String>) path;
        }

        return root.get(field);
    }

}
