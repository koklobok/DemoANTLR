# ANTLR Demo

This repository contains demo application for introduction to ANTLR parser generator. 
Language to be parsed is Boolean algebra expressions. 

Rules of grammar developed for this project:
* a valid expression is either variable or boolean constant
* valid expressions can be used as operands in boolean operations AND, OR, negation
* a valid expression can be grouped with parentheses and remain a valid expression 

Token | Rule 
------|------
A variable | Single Latin letter 
AND operation | 'AND' or '&'   
OR operation |  'OR' or '&#124;'
Negation operation | 'NOT'  or '!' 
True constant | 'TRUE' or 'true'
False constant | 'FALSE' or 'false'

Examples of expressions:
* A
* !false
* !(A | B)

## How to run it

There are two Java applications to show how to work with ANTLR Listener and Visitor. In order to run examples without IDE 
you need to build jar file with Maven.

Listener demo is a simple console program which will print parse tree of a given list of expressions each specified 
 in the new line. To run it just execute ``com.koklobok.PrintParseTree`` class either from your IDE or with a command   
 &nbsp;&nbsp;&nbsp;&nbsp;``java -cp target/demoANTLR.jar com.koklobok.PrintParseTree``  
 and enter boolean algebra expressions each in new line. To stop input press Ctrl+Z and Enter on Windows or Ctrl+D on Linux.
    
Visitor demo is run by executing ``com.koklobok.LogicalExprEval`` class from IDE or just clicking on demoANTLR.jar file. It is a JavaFX application
   where you can check identity of two expressions. Specify two boolean expressions with equal sign between them in one line
   and program will verify if those two expressions will be equal to each other for all possible values of variables. 
   You can check some rules of boolean algebra to see if they not fooled you, e.g. one of the De Morgan's laws  
   &nbsp;&nbsp;&nbsp;&nbsp;``!(A | B) = !A & !B``

## Learn ANTLR

Book from ANTLR author Terence Parr "The Definitive ANTLR 4 Reference" [on Amazon](https://www.amazon.com/Definitive-ANTLR-4-Reference/dp/1934356999)  
[GitHub repo](https://github.com/antlr/grammars-v4) with immense list of grammar examples for ANTLR