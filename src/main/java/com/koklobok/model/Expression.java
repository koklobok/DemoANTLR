package com.koklobok.model;

import java.util.Map;
import java.util.Set;

/**
 * Interface for operation which should be supported by all types of expressions. 
 * 
 * @author Roman.Holiuk
 */
public interface Expression {

    /**
     * Retrieve unmodifiable set of variables in expression.
     * 
     * @return Unmodifiable set of variables, if no variables defined in expression will return empty collection.
     */
    Set<Variable> getVariables();

    /**
     * Evaluate logic expression to boolean value with variables values passed in.
     * 
      * @param parameters Map of values for variables defined in expression
     * @return boolean result of expression
     * @throws VariableValueNotDefined if expression has one or more variable which is not found in parameters Map
     */
    boolean evaluate(Map<Variable, Boolean> parameters) throws VariableValueNotDefined;

}
