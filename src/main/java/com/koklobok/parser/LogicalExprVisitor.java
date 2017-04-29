package com.koklobok.parser;

import com.koklobok.grammar.BooleanLogicBaseVisitor;
import com.koklobok.grammar.BooleanLogicParser;
import com.koklobok.model.AndExpression;
import com.koklobok.model.Expression;
import com.koklobok.model.FalseConstant;
import com.koklobok.model.NegateOperation;
import com.koklobok.model.OrExpression;
import com.koklobok.model.TrueConstant;
import com.koklobok.model.Variable;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

/**
 * @author Roman.Holiuk
 */
public class LogicalExprVisitor extends BooleanLogicBaseVisitor<Expression> {

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, Expression currentResult) {
        return currentResult == null;
    }

    @Override
    public Expression visitExpr_OR(BooleanLogicParser.Expr_ORContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        
        return new OrExpression(left, right);
    }

    @Override
    public Expression visitExpr_AND(BooleanLogicParser.Expr_ANDContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        return new AndExpression(left, right);
    }
    
    @Override
    public Expression visitNot_var(BooleanLogicParser.Not_varContext ctx) {
        Expression variable = visitVar(ctx.var());
        return new NegateOperation(variable);
    }

    @Override
    public Expression visitGroup(BooleanLogicParser.GroupContext ctx) {
        return super.visitGroup(ctx);
    }

    @Override
    public Expression visitNot_group(BooleanLogicParser.Not_groupContext ctx) {
        Expression subExpression = visit(ctx.group());
        return new NegateOperation(subExpression);
    }

    @Override
    public Expression visitNot_constant(BooleanLogicParser.Not_constantContext ctx) {
        Expression constantExpression = visitConstant_expr(ctx.constant_expr());
        return new NegateOperation(constantExpression);
    }

    @Override
    public Expression visitVar(BooleanLogicParser.VarContext ctx) {
        return new Variable(ctx.getText());
    }

    @Override
    public Expression visitTrue(BooleanLogicParser.TrueContext ctx) {
        return new TrueConstant();
    }

    @Override
    public Expression visitFalse(BooleanLogicParser.FalseContext ctx) {
        return new FalseConstant();
    }

    @Override
    public Expression visit(ParseTree tree) {
        if (tree == null) return null;

        return super.visit(tree);
    }
}
