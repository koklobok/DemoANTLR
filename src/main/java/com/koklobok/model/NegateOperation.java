package com.koklobok.model;

import java.util.Map;

/**
 * @author Roman.Holiuk
 */
public class NegateOperation extends UnaryOperation {
    
    public NegateOperation(Expression subExpression) {
        super(subExpression);
    }

    @Override
    public boolean evaluate(Map<Variable, Boolean> parameters) throws VariableValueNotDefined {
        return !getSubExpression().evaluate(parameters);
    }
}
