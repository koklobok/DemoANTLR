package com.koklobok;

import com.koklobok.grammar.BooleanLogicLexer;
import com.koklobok.grammar.BooleanLogicParser;
import com.koklobok.parser.PrintContextListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Demo application to parse input stream and print parse tree to system output. 
 * 
 * @author Roman.Holiuk
 */
public class PrintParseTree {
    public static void main(String[] args) throws IOException {

        String input = readInputFromConsole();
        
        ANTLRInputStream stream = new ANTLRInputStream(input);
        BooleanLogicLexer lexer = new BooleanLogicLexer(stream);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BooleanLogicParser parser = new BooleanLogicParser(tokens);

        ParseTree tree = parser.expr_list();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        PrintContextListener listener = new PrintContextListener();
        walker.walk(listener, tree);


    }

    private static String readInputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        StringJoiner stringJoiner = new StringJoiner("\n");
        while (true) {
            try {
                stringJoiner.add(scanner.nextLine());
            } catch (Exception e) {
                return stringJoiner.toString();
            }
        }
    }
    
}
