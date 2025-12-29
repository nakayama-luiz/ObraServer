package com.diario.obra.utils;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

public class RsqlSpecificationBuilder {
    public static <T> Specification<T> build(String search) {
        Node rootNode = new RSQLParser().parse(search);
        return rootNode.accept(new RsqlVisitor<>());
    }
}
