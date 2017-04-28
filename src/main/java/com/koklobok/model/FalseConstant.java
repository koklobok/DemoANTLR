package com.koklobok.model;

/**
 * @author Roman.Holiuk
 */
public class FalseConstant extends LogicConstant {

    public FalseConstant() {
        super(false);
    }

    @Override
    public String toString() {
        return "FALSE";
    }
}
