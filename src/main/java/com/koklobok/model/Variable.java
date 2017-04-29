package com.koklobok.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman.Holiuk
 */
public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of the logic variable can not be empty.");
        }
        
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet<>();
        variables.add(this);
        return Collections.unmodifiableSet(variables);
    }
    
    @Override
    public boolean evaluate(Map<Variable, Boolean> parameters) {
        Boolean result = parameters.get(this);
        if (result == null) throw new VariableValueNotDefined(this.getName());
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;

        return name.equals(variable.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
