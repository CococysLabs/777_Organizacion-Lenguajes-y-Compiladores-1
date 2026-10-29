grammar Expr;

expr   : expr '+' term     # Add
       | term               # Term
       ;

term   : term '*' factor   # Multiply
       | factor             # Factor
       ;

factor : INT                # Int
       ;

INT    : [0-9]+ ;

WS     : [ \t\r\n]+ -> skip ;