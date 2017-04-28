package com.koklobok.model;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman.Holiuk
 */
public abstract class LogicConstant implements Expression {

    private final boolean value;

    protected LogicConstant(boolean value) {
        this.value = value;
    }

    @Override
    public Set<Variable> getVariables() {
        return Collections.emptySet();
    }

    @Override
    public boolean evaluate(Map<Variable, Boolean> parameters) throws VariableValueNotDefined {
        return value;
    }
}
