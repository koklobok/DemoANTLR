package com.koklobok.parser;

import com.koklobok.model.LogicalExpr;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Roman.Holiuk
 */
public class AntlrLogicalParserTest {
    private AntlrLogicalExprParser parser;

    @Before
    public void setUp() {
        parser = new AntlrLogicalExprParser();
    }
    
    @Test
    public void parseVariable() {
        LogicalExpr expr = parser.parseExpression("A");
        List<LogicalExpr> variables = expr.variables();
        assertThat(variables, hasSize(1));
    }
}
