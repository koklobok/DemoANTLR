package com.koklobok.parser;

import com.koklobok.grammar.BooleanLogicLexer;
import com.koklobok.grammar.BooleanLogicParser;
import com.koklobok.model.BooleanStatement;
import com.koklobok.model.Expression;
import org.antlr.v4.runtime.*;

/**
 * @author Roman.Holiuk
 */
public class BooleanStatementParser {

    public BooleanStatement parse(String text) {
        ANTLRInputStream stream = new ANTLRInputStream(text);
        BooleanLogicLexer lexer = new BooleanLogicLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BooleanLogicParser parser = new BooleanLogicParser(tokens);
        BooleanLogicParser.StatContext tree;
        parser.getErrorListeners().clear();
        lexer.getErrorListeners().clear();
        RecordParseErrorListener errorListener = new RecordParseErrorListener();
        parser.addErrorListener(errorListener);
        lexer.addErrorListener(errorListener);
        Expression left = null;
        Expression right = null;
        tree = parser.stat();
        if (!errorListener.hasErrors()) {
            LogicalExprVisitor visitor = new LogicalExprVisitor();
            left = visitor.visit(tree.expr(0));
            right = visitor.visit(tree.expr(1));
        }
        return new BooleanStatement(left, right);
    }

    private static class RecordParseErrorListener extends BaseErrorListener {
        private boolean recordedError = false;

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            recordedError = true;
        }

        boolean hasErrors() {
            return recordedError;
        }
    }

}
