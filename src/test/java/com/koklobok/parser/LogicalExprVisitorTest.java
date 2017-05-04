package com.koklobok.parser;

import com.koklobok.grammar.BooleanLogicLexer;
import com.koklobok.grammar.BooleanLogicParser;
import com.koklobok.model.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Roman.Holiuk
 */
public class LogicalExprVisitorTest {
    
    @Test
    public void trueConstExpression() {
        String text = "TRUE";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(0));
        assertThat("Expression should be TRUE const", expression, instanceOf(TrueConstant.class));
    }

    @Test
    public void falseConstExpression() {
        String text = "FALSE";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(0));
        assertThat("Expression should be FALSE const", expression, instanceOf(FalseConstant.class));
    }

    @Test
    public void twoEqualVariablesOr() {
        String text = "A | A";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(1));
        assertThat("Expression should be OR", expression, instanceOf(OrOperation.class));
    }

    @Test
    public void twoEqualVariablesAnd() {
        String text = "A & A";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(1));
        assertThat("Expression should be AND", expression, instanceOf(AndOperation.class));
    }

    @Test
    public void twoDistinctVariablesOr() {
        String text = "A | B";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(2));
        assertThat("Expression should be OR", expression, instanceOf(OrOperation.class));
    }

    @Test
    public void twoDistinctVariablesAnd() {
        String text = "A & B";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(2));
        assertThat("Expression should be AND", expression, instanceOf(AndOperation.class));
    }

    @Test
    public void simpleGroupOr() {
        String text = "(A | B)";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(2));
        assertThat("Expression should be OR", expression, instanceOf(OrOperation.class));
    }


    @Test
    public void simpleGroupAND() {
        String text = "(A & B)";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(2));
        assertThat("Expression should be AND", expression, instanceOf(AndOperation.class));
    }

    @Test
    public void expressionPriority_AND_higher_OR() {
        String text = "A | B & C";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(3));
        assertThat("Higher level expression should be OR", expression, instanceOf(OrOperation.class));
        Expression subExpression = ((OrOperation) expression).getRight();
        assertThat("Left subexpression should be AND", subExpression, instanceOf(AndOperation.class));
        AndOperation andOperation = (AndOperation) subExpression;
        assertThat("Left part of AND should be variable", andOperation.getLeft(), instanceOf(Variable.class));
        assertThat("Right part of AND should be variable", andOperation.getRight(), instanceOf(Variable.class));
    }

    @Test
    public void expressionPriority_Parentheses() {
        String text = "(A | B) & C";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(3));
        assertThat("Higher level expression should be AND", expression, instanceOf(AndOperation.class));
        Expression subExpression = ((AndOperation) expression).getLeft();
        assertThat("Left subexpression should be OR", subExpression, instanceOf(OrOperation.class));
        OrOperation andExpression = (OrOperation) subExpression;
        assertThat("Left part of AND should be variable", andExpression.getLeft(), instanceOf(Variable.class));
        assertThat("Right part of AND should be variable", andExpression.getRight(), instanceOf(Variable.class));
    }

    @Test
    public void notVariable() {
        String text = "!A";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have one variable", expression.getVariables(), hasSize(1));
        assertThat("Expression should be NOT", expression, instanceOf(NegateOperation.class));
        
    }

    @Test
    public void notConst_TRUE() {
        String text = "!TRUE";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(0));
        assertThat("Expression should be NOT", expression, instanceOf(NegateOperation.class));

    }

    @Test
    public void notConst_FALSE() {
        String text = "!FALSE";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(0));
        assertThat("Expression should be NOT", expression, instanceOf(NegateOperation.class));

    }

    @Test
    public void notGroup() {
        String text = "!(A | B)";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(2));
        assertThat("Expression should be NOT", expression, instanceOf(NegateOperation.class));

    }

    @Test
    public void notInsideGroup() {
        String text = "(!A | B)";

        Expression expression = executeParserWithVisitor(text);

        assertNotNull("Expression should not be null", expression);
        assertThat("Expression should have no variables", expression.getVariables(), hasSize(2));
        assertThat("Expression should be OR", expression, instanceOf(OrOperation.class));

    }

    private Expression executeParserWithVisitor(String text) {
        ANTLRInputStream stream = new ANTLRInputStream(text);
        BooleanLogicLexer lexer = new BooleanLogicLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BooleanLogicParser parser = new BooleanLogicParser(tokens);

        ParseTree tree = parser.expr();
        LogicalExprVisitor visitor = new LogicalExprVisitor();

        return visitor.visit(tree);
    }
}