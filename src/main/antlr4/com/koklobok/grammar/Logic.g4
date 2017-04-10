grammar Logic;

stat: expr '=' expr;

expr: expr AND expr     #and 
    | expr OR expr      #or
    | var               #variable_expr
    | constant          #constant_expr
    | '(' expr ')'      #paren
    | NOT '(' expr ')'  #not_expr
;

var: VAR        #variable
    | NOT var   #not_variable
;    

constant: TRUE      #true
    | FALSE         #false
    | NOT constant  #not_constant
;

TRUE: 'true';
FALSE: 'false';
AND: 'AND' | '&';
OR:  'OR' | '|';
VAR: [A-Z]+;
NOT: 'NOT' | '!';
NL:  '\r'? '\n';
WS:  [ \t] -> skip;