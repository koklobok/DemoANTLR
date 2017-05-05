grammar BooleanLogic;

/* Statement which consists of two boolean algebra expression separated by equal sign */
stat: expr '=' expr EOF;

/* One or more boolean algebra expressions separated by new line */
expr_list: expr (NL expr)*;

/* Boolean algebra expression */
expr: expr AND expr         #expr_AND      
    | expr OR expr          #expr_OR
    | negate                #expr_NOT
    | var                   #expr_VAR
    | constant              #expr_CONST
    | group                 #expr_GROUP
;

/* Parser rule for enabling parentheses */  
group: '(' expr ')' ;


/* Negation operation. 
 We need separate rule for negation as simple rule 'NOT expr' will bring ambiguity e.g., string "(!A | B)" with this rule
 can be parsed as negation of A|B expression, because it is a valid expression. 
*/
negate: NOT var             #not_var
    | NOT group             #not_group
    | NOT constant_expr     #not_constant
; 

var: VAR ;

constant_expr: constant;

constant: TRUE      #true
    | FALSE         #false
;

TRUE: 'true' | 'TRUE';
FALSE: 'false' | 'FALSE';
AND: 'AND' | '&';
OR:  'OR' | '|';
VAR: [A-Z];
NOT: 'NOT' | '!';
NL:  '\r'? '\n';                /* Handle line delimiters */
WS:  [ \t] -> skip;             /* Skip whitespaces */