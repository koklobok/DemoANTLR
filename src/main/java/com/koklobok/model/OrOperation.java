package com.koklobok.model;

import java.util.Map;

/**
 * @author Roman.Holiuk
 */
public class OrOperation extends BinaryOperation {

    public OrOperation(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public boolean evaluate(Map<Variable, Boolean> parameters) throws VariableValueNotDefined {
        return getLeft().evaluate(parameters) 
                || getRight().evaluate(parameters);

    }

    @Override
    public String toString() {
        return "( " + getLeft().toString() + " | " + getRight().toString() + " ) ";
    }
}
