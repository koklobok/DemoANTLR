grammar BooleanLogic;

stat: expr '=' expr EOF;

expr: expr AND expr         #expr_AND      
    | expr OR expr          #expr_OR
    | negate                #expr_NOT
    | var                   #expr_VAR
    | constant              #expr_CONST
    | group                 #expr_GROUP
;

group: '(' expr ')' ;

negate: NOT var             #not_var
    | NOT group             #not_group
    | NOT constant_expr     #not_constant
; 

var: VAR ;

constant_expr: constant;

constant: TRUE      #true
    | FALSE         #false
;

TRUE: 'true';
FALSE: 'false';
AND: 'AND' | '&';
OR:  'OR' | '|';
VAR: [A-Z];
NOT: 'NOT' | '!';
NL:  '\r'? '\n';
WS:  [ \t] -> skip;