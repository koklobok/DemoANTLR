package com.koklobok.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Roman.Holiuk
 */
public abstract class BinaryOperation implements Expression {
    private final Expression left;
    private final Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet<>();
        variables.addAll(left.getVariables());
        variables.addAll(right.getVariables());

        return Collections.unmodifiableSet(variables);
    }
}
