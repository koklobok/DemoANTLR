package com.koklobok.model;

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
}
