package com.koklobok.model;

/**
 * @author Roman.Holiuk
 */
public class VariableValueNotDefined extends RuntimeException {
    private static final long serialVersionUID = 6568588784218984629L;

    public VariableValueNotDefined(String name) {
        super("Value for variable " + name + " is not defined");
    }
}
