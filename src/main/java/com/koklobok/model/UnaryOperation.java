package com.koklobok.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Roman.Holiuk
 */
public abstract class UnaryOperation implements Expression {
    private final Expression subExpression;

    public UnaryOperation(Expression subExpression) {
        this.subExpression = subExpression;
    }

    public Expression getSubExpression() {
        return subExpression;
    }

    @Override
    public Set<Variable> getVariables() {
        return subExpression.getVariables();
    }
}
