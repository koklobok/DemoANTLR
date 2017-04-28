package com.koklobok.parser;

import com.koklobok.grammar.LogicBaseVisitor;
import com.koklobok.grammar.LogicParser;
import com.koklobok.model.AndExpression;
import com.koklobok.model.Expression;
import com.koklobok.model.FalseConstant;
import com.koklobok.model.NegateOperation;
import com.koklobok.model.OrExpression;
import com.koklobok.model.TrueConstant;
import com.koklobok.model.Variable;

/**
 * @author Roman.Holiuk
 */
public class LogicalExprVisitor extends LogicBaseVisitor<Expression> {

    @Override
    public Expression visitExpr_OR(LogicParser.Expr_ORContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        
        return new OrExpression(left, right);
    }

    @Override
    public Expression visitExpr_AND(LogicParser.Expr_ANDContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        return new AndExpression(left, right);
    }
    
    @Override
    public Expression visitNot_var(LogicParser.Not_varContext ctx) {
        Expression variable = visitVar(ctx.var());
        return new NegateOperation(variable);
    }

    @Override
    public Expression visitNot_group(LogicParser.Not_groupContext ctx) {
        Expression subExpression = visit(ctx.group());
        return new NegateOperation(subExpression);
    }

    @Override
    public Expression visitNot_constant(LogicParser.Not_constantContext ctx) {
        Expression constantExpression = visitConstant_expr(ctx.constant_expr());
        return new NegateOperation(constantExpression);
    }

    @Override
    public Expression visitVar(LogicParser.VarContext ctx) {
        return new Variable(ctx.getText());
    }

    @Override
    public Expression visitTrue(LogicParser.TrueContext ctx) {
        return new TrueConstant();
    }

    @Override
    public Expression visitFalse(LogicParser.FalseContext ctx) {
        return new FalseConstant();
    }
}
