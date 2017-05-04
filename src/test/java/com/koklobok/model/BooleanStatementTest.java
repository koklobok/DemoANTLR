package com.koklobok.model;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Roman.Holiuk
 */
public class BooleanStatementTest {


    @Test
    public void evaluateTrue_True() throws Exception {
        Expression left = new TrueConstant();
        Expression right = new TrueConstant();

        verifyExpressionResult(left, right, true);
    }


    @Test
    public void evaluateFalse_False() throws Exception {
        Expression left = new FalseConstant();
        Expression right = new FalseConstant();

        verifyExpressionResult(left, right, true);
    }

    @Test
    public void evaluateTrue_False() throws Exception {
        Expression left = new TrueConstant();
        Expression right = new FalseConstant();

        verifyExpressionResult(left, right, false);
    }


    @Test
    public void evaluateTrue_NotFalse() throws Exception {
        Expression left = new TrueConstant();
        Expression right = new NegateOperation(new FalseConstant());

        verifyExpressionResult(left, right, true);
    }

    @Test
    public void evaluateAnd_Or() throws Exception {
        Expression left = new AndOperation(
                new OrOperation(new Variable("A"), new Variable("B")),
                new Variable("C")
        );
        Expression right = new OrOperation(
                new AndOperation(new Variable("A"), new Variable("C")),
                new AndOperation(new Variable("B"), new Variable("C"))
        );

        verifyExpressionResult(left, right, true);
    }

    @Test
    public void evaluateVar_And_False() throws Exception {
        Expression left = new Variable("A");
        Expression right = new AndOperation(
                new Variable("A"),
                new FalseConstant()
        );

        verifyExpressionResult(left, right, false);
    }


    private void verifyExpressionResult(Expression leftExpression, Expression rightExpression, boolean expectedResult) {
        BooleanStatement statement = new BooleanStatement(leftExpression, rightExpression);

        assertThat(statement, evaluatesTo(expectedResult));
    }

    private static Matcher<BooleanStatement> evaluatesTo(boolean expected) {
        return new StatementMatcher(expected);
    }

    private static class StatementMatcher extends DiagnosingMatcher<BooleanStatement> {

        private final boolean expected;

        StatementMatcher(boolean expected) {
            this.expected = expected;
        }

        @Override
        protected boolean matches(Object item, Description mismatch) {
            if (item == null) {
                mismatch.appendText(" Statement is null ");
                return false;
            }

            if (!(item instanceof BooleanStatement)) {
                mismatch.appendValue(item).appendText(" is not of type BooleanStatement");
                return false;
            }
            BooleanStatement statement = (BooleanStatement) item;
            boolean actual = statement.evaluate();
            if (actual != expected) {
                mismatch.appendValue(item).appendText(" evaluates to <" + actual + ">");
                return false;
            }

            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("evaluates to ").appendText("<" + expected + ">");
        }
    }

}