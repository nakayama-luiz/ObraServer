package com.diario.obra.utils;

import cz.jirutka.rsql.parser.ast.*;
import org.springframework.data.jpa.domain.Specification;
public class RsqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    @Override
    public Specification<T> visit(AndNode node, Void param) {
        return node.getChildren()
                .stream()
                .map(n -> n.accept(this, null))
                .reduce(Specification::and)
                .orElse(null);
    }

    @Override
    public Specification<T> visit(OrNode node, Void param) {
        return node.getChildren()
                .stream()
                .map(n -> n.accept(this, null))
                .reduce(Specification::or)
                .orElse(null);
    }

    @Override
    public Specification<T> visit(ComparisonNode node, Void param) {
        return new RsqlSpecification<>(node);
    }
}
