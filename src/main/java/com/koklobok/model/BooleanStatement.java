package com.koklobok.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman.Holiuk
 */
public class BooleanStatement {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public BooleanStatement(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    /**
     * Checks if left part of expression equals right part for all possible values of variables.
     * @return true if expressions are equal
     */
    public boolean evaluate() {
        Set<Variable> variables = new HashSet<>();
        variables.addAll(leftExpression.getVariables());
        variables.addAll(rightExpression.getVariables());
        
        if (variables.size() == 0 ) {
            Map<Variable, Boolean> emptyParameters = Collections.emptyMap();
            return leftExpression.evaluate(emptyParameters) == rightExpression.evaluate(emptyParameters);
        }
        
        boolean result = true;
        Iterator<Map<Variable, Boolean>> iterator = new ParametersIterator(variables);
        while (result && iterator.hasNext()) {
            Map<Variable, Boolean> parameters = iterator.next();
            result = leftExpression.evaluate(parameters) == rightExpression.evaluate(parameters);
        }
        
        return result;
    }

    private static class ParametersIterator implements Iterator<Map<Variable, Boolean>> {

        private final List<Variable> variables;
        private int currentIndex;
        
        ParametersIterator(Set<Variable> variables) {
            this.variables = new ArrayList<>(variables);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < variables.size() - 1;
        }

        @Override
        public Map<Variable, Boolean> next() {
            int size = variables.size();
            Map<Variable, Boolean> parameters = new HashMap<>();
            for (int i = size - 1; i >=0; i--) {
                boolean value = (currentIndex & (1 << i)) != 0;
                parameters.put(variables.get(i), value);
            }
            currentIndex++;
            return parameters;
        }
    }

    @Override
    public String toString() {
        return leftExpression + " = " + rightExpression;
    }
}
