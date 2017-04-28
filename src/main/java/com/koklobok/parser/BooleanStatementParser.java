package com.koklobok.parser;

import com.koklobok.grammar.BooleanLogicLexer;
import com.koklobok.grammar.BooleanLogicParser;
import com.koklobok.model.BooleanStatement;
import com.koklobok.model.Expression;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @author Roman.Holiuk
 */
public class BooleanStatementParser {

    public BooleanStatement parse(String text) {
        CharStream charStream = CharStreams.fromString(text);
        BooleanLogicLexer lexer = new BooleanLogicLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BooleanLogicParser parser = new BooleanLogicParser(tokens);

        BooleanLogicParser.StatContext tree = parser.stat();
        LogicalExprVisitor visitor = new LogicalExprVisitor();
        Expression left = visitor.visit(tree.expr(0));
        Expression right = visitor.visit(tree.expr(1));

        return new BooleanStatement(left, right);
    }
    
    
    
}
