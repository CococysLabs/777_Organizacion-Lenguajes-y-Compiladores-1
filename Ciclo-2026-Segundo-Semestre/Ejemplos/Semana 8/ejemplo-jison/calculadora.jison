%lex
%%

\s+                   /* ignorar espacios */
[0-9]+("."[0-9]+)?\b  return 'Decimal';
[0-9]+                  return 'Entero';
"+"                   return 'mas';
"-"                   return 'menos';
";"                    return 'puntoComa'
"("                   return '(';
")"                   return ')';
<<EOF>>               return 'EOF';

/lex

%start expressions

%%

expressions
    : e EOF { return $1; }
    ;

e
    : e '+' e { $$ = $1 + $3; }
    | e '-' e { $$ = $1 - $3; }
    | e '*' e { $$ = $1 * $3; }
    | e '/' e { $$ = $1 / $3; }
    | '(' e ')' { $$ = $2; }
    | NUMBER { $$ = Number(yytext); }
    ;

   


/*
// ====================================================
// EJEMPLO BATTLESCRIPT: DEMOSTRACIÓN DE HISTORIAL
// ====================================================
mage Merlin {
  initial: ARCANE_BOLT
  rules: [
    if round_number >= 1 && last_move(opponent_history) == SLASH then MAGIC_BARRIER,
    if self_resource <= 20 then MEDITATE,
    else FIREBALL
  ]
}
warrior Ragnar {
  initial: SLASH
  rules: [
    if self_resource <= 20 then REST,
    else SLASH
  ]
}
match DueloEstrategico {
  players: [Merlin, Ragnar]
  rounds: 5
}
main {
  run [DueloEstrategico] with {
    seed: 42
  }
}*/