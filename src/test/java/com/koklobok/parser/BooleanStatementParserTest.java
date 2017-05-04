package com.koklobok.parser;

import com.koklobok.model.AndOperation;
import com.koklobok.model.BooleanStatement;
import com.koklobok.model.Expression;
import com.koklobok.model.FalseConstant;
import com.koklobok.model.OrOperation;
import com.koklobok.model.TrueConstant;
import com.koklobok.model.Variable;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

/**
 * @author Roman.Holiuk
 */
public class BooleanStatementParserTest {
    private BooleanStatementParser parser;
    
    @Before
    public void setUp() {
        parser = new BooleanStatementParser();
    }
    
    
    @Test
    public void constExpression() throws Exception {
        String text = "TRUE = FALSE";

        BooleanStatement statement = parser.parse(text);
        Expression leftExpression = statement.getLeftExpression();
        Expression rightExpression = statement.getRightExpression();
        
        assertNotNull("Left part of statement not null", leftExpression);
        assertNotNull("Right part of statement not null", rightExpression);

        assertThat("Left part should have no variables", leftExpression.getVariables(), hasSize(0));
        assertThat("Right part should have no variables", rightExpression.getVariables(), hasSize(0));
        
        assertThat("Left part should be true", leftExpression, instanceOf(TrueConstant.class));
        assertThat("Right part should be false", rightExpression, instanceOf(FalseConstant.class));
    }


    @Test
    public void variablesExpression() throws Exception {
        String text = "A = B";

        BooleanStatement statement = parser.parse(text);
        Expression leftExpression = statement.getLeftExpression();
        Expression rightExpression = statement.getRightExpression();

        assertNotNull("Left part of statement not null", leftExpression);
        assertNotNull("Right part of statement not null", rightExpression);

        assertThat("Left part should have one variable", leftExpression.getVariables(), hasSize(1));
        assertThat("Right part should have one variable", rightExpression.getVariables(), hasSize(1));

        assertThat("Left expression should be variable A", leftExpression, is(new Variable("A")));
        assertThat("Right expression should be variable B", rightExpression, is(new Variable("B")));
    }

    @Test
    public void operationExpression() throws Exception {
        String text = "(A & A) = (A | B)";

        BooleanStatement statement = parser.parse(text);
        Expression leftExpression = statement.getLeftExpression();
        Expression rightExpression = statement.getRightExpression();

        assertNotNull("Left part of statement not null", leftExpression);
        assertNotNull("Right part of statement not null", rightExpression);

        assertThat("Left part should have one variable", leftExpression.getVariables(), hasSize(1));
        assertThat("Right part should have two variables", rightExpression.getVariables(), hasSize(2));

        assertThat("Left expression should be AND operation", leftExpression, instanceOf(AndOperation.class));
        assertThat("Right expression should be OR operation", rightExpression, instanceOf(OrOperation.class));
        
    }

}