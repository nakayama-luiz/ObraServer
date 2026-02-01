package com.diario.obra.utils;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.UUID;

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

        Path<?> path = getPath(root, field);
        Class<?> fieldType = path.getJavaType();

        return switch (node.getOperator().getSymbol()) {
            case "==" -> {
                if (fieldType == UUID.class) {
                    yield cb.equal(path, UUID.fromString(value));
                } else if (fieldType == String.class) {
                    yield cb.like(
                            cb.lower(path.as(String.class)),
                            value.toLowerCase().replace("*", "%")
                    );
                } else {
                    yield cb.equal(path, value);
                }
            }
            case "!=" -> {
                if (fieldType == UUID.class) {
                    yield cb.notEqual(path, UUID.fromString(value));
                } else {
                    yield cb.notEqual(path, value);
                }
            }
            default -> throw new UnsupportedOperationException(
                    "Operador não suportado: " + node.getOperator()
            );
        };
    }

    private Path<?> getPath(Root<?> root, String field) {
        if (field.contains(".")) {
            String[] parts = field.split("\\.");
            Path<?> path = root;

            for (String part : parts) {
                path = path.get(part);
            }

            return path;
        }

        return root.get(field);
    }

}
