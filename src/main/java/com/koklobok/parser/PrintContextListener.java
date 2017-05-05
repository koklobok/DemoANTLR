package com.koklobok.parser;

import com.koklobok.grammar.BooleanLogicBaseListener;
import com.koklobok.grammar.BooleanLogicParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * ANTLR listener for BooleanLogic grammar to print parse tree nodes to system output stream
 * 
 * @author Roman.Holiuk
 */
public class PrintContextListener extends BooleanLogicBaseListener {
    private int currLevel;

    @Override
    public void enterExpr_VAR(BooleanLogicParser.Expr_VARContext ctx) {
        printEnterRule("VAR", ctx.getText());
    }

    @Override
    public void enterExpr_OR(BooleanLogicParser.Expr_ORContext ctx) {
        printEnterRule("OR", ctx.getText());
    }

    @Override
    public void enterExpr_AND(BooleanLogicParser.Expr_ANDContext ctx) {
        printEnterRule("AND", ctx.getText());
    }

    @Override
    public void exitExpr_VAR(BooleanLogicParser.Expr_VARContext ctx) {
        printExitRule("VAR", ctx.getText());
    }

    @Override
    public void exitExpr_OR(BooleanLogicParser.Expr_ORContext ctx) {
        printExitRule("OR", ctx.getText());
    }

    @Override
    public void exitExpr_AND(BooleanLogicParser.Expr_ANDContext ctx) {
        printExitRule("AND", ctx.getText());
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        currLevel++;
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        currLevel--;
    }

    private void printEnterRule(String kindOfExpression, String text) {
        System.out.printf("%s<< Entering %s expression: %s\n",
                levelOffset(),
                kindOfExpression,
                text);
    }

    private void printExitRule(String kindOfExpression, String text) {
        System.out.printf("%s>> Exiting %s expression: %s\n",
                levelOffset(),
                kindOfExpression,
                text);
    }
    
    private String levelOffset() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < currLevel; i++) {
            stringBuilder.append("  ");
        }
        
        return stringBuilder.toString();
    }
}
