package Analizadores;

import java_cup.runtime.Symbol;
import java.util.LinkedList;

%%

%{


%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

%cup
%class scanner
%public
%line
%char
%column
%full
%ignorecase


// ========================================
// PALABRAS RESERVADAS
// ========================================

MAGE = "mage"
WARRIOR = "warrior"

INITIAL = "initial"
RULES = "rules"
IF = "if"
THEN = "then"
ELSE = "else"
SELF_RESOURCE = "self_resource"

MATCH = "match"
PLAYERS = "players"
ROUNDS = "rounds"

MAIN = "main"
RUN = "run"
WITH = "with"
SEED = "seed"


// ========================================
// OPERADORES
// ========================================

MENOR_IGUAL = "<="
MAYOR_IGUAL = ">="
IGUAL = "=="
DIFERENTE = "!="
MENOR = "<"
MAYOR = ">"


// ========================================
// SIMBOLOS
// ========================================

LLAVE1 = "{"
LLAVE2 = "}"

CORCHETE1 = "["
CORCHETE2 = "]"

DOS_PUNTOS = ":"
COMA = ","


// ========================================
// EXPRESIONES
// ========================================

BLANCOS = [ \r\t\n\f]+

ENTERO = [0-9]+

ID = [a-zA-Z][a-zA-Z0-9_]*


// ========================================
// COMENTARIOS
// ========================================

COMENTARIO_SIMPLE =
    "//"[^\n]*

COMENTARIO_MULTILINEA =
    "/*"([^*]|\*+[^*/])*"*"+"/"


%%


// ========================================
// COMENTARIOS
// ========================================

{COMENTARIO_SIMPLE} {
    // Ignorar
}

{COMENTARIO_MULTILINEA} {
    // Ignorar
}


// ========================================
// PALABRAS RESERVADAS
// ========================================

{MAGE} {
    return new Symbol(sym.MAGE,yycolumn,yyline,yytext());
}

{WARRIOR} {
    return new Symbol(sym.WARRIOR,yycolumn,yyline,yytext());
}

{INITIAL} {
    return new Symbol(sym.INITIAL,yycolumn,yyline,yytext());
}

{RULES} {
    return new Symbol(sym.RULES,yycolumn,yyline,yytext());
}

{IF} {
    return new Symbol(sym.IF,yycolumn,yyline,yytext());
}

{THEN} {
    return new Symbol(sym.THEN,yycolumn,yyline,yytext());
}

{ELSE} {
    return new Symbol(sym.ELSE,yycolumn,yyline,yytext());
}

{SELF_RESOURCE} {
    return new Symbol(sym.SELF_RESOURCE,yycolumn,yyline,yytext());
}

{MATCH} {
    return new Symbol(sym.MATCH,yycolumn,yyline,yytext());
}

{PLAYERS} {
    return new Symbol(sym.PLAYERS,yycolumn,yyline,yytext());
}

{ROUNDS} {
    return new Symbol(sym.ROUNDS,yycolumn,yyline,yytext());
}

{MAIN} {
    return new Symbol(sym.MAIN,yycolumn,yyline, yytext());
}

{RUN} {
    return new Symbol(sym.RUN,yycolumn,yyline,yytext());
}

{WITH} {
    return new Symbol(sym.WITH,yycolumn,yyline,yytext());
}

{SEED} {
    return new Symbol(sym.SEED,yycolumn,yyline,yytext());
}


// ========================================
// OPERADORES
// ========================================

{MENOR_IGUAL} {
    return new Symbol(sym.MENOR_IGUAL,yycolumn,yyline,yytext());
}

{MAYOR_IGUAL} {
    return new Symbol(sym.MAYOR_IGUAL,yycolumn,yyline,yytext());
}

{IGUAL} {
    return new Symbol(sym.IGUAL,yycolumn,yyline,yytext());
}

{DIFERENTE} {
    return new Symbol(sym.DIFERENTE,yycolumn,yyline,yytext());
}

{MENOR} {
    return new Symbol(sym.MENOR,yycolumn,yyline,yytext());
}

{MAYOR} {
    return new Symbol(sym.MAYOR,yycolumn,yyline,yytext());
}


// ========================================
// SIMBOLOS
// ========================================

{LLAVE1} {
    return new Symbol(sym.LLAVE1,yycolumn,yyline,yytext());
}

{LLAVE2} {
    return new Symbol(sym.LLAVE2,yycolumn,yyline,yytext());
}

{CORCHETE1} {
    return new Symbol(sym.CORCHETE1,yycolumn, yyline,yytext());
}

{CORCHETE2} {
    return new Symbol(sym.CORCHETE2,yycolumn, yyline,  yytext());
}

{DOS_PUNTOS} {
    return new Symbol(sym.DOS_PUNTOS, yycolumn, yyline, yytext());
}

{COMA} {
    return new Symbol(sym.COMA,yycolumn,yyline,yytext());
}


// ========================================
// DATOS
// ========================================

{ENTERO} {
    return new Symbol(sym.ENTERO, yycolumn,yyline,yytext());
}

{ID} {
    return new Symbol(sym.ID,yycolumn,yyline, yytext());
}


// ========================================
// ESPACIOS
// ========================================

{BLANCOS} {
    // Ignorar
}


// ========================================
// ERROR LEXICO
// ========================================

. {
    String error =
        "Simbolo no reconocido: "
        + yytext()
        + " linea: "
        + yyline
        + " columna: "
        + yycolumn;

    System.out.println(error);
}