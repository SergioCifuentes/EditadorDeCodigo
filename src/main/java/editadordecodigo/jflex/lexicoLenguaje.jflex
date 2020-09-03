package editadordecodigo.jflex;
import java_cup.runtime.Symbol;
import editadordecodigo.cup.SimbolosLenguaje;
%%
%public
%class AnalizadorLexicoLenguaje
%cupsym SimbolosLenguaje
%cup
%cupdebug
%line
%column

/*Identifiers*/
Letra = [a-zA-ZÀ-ÿ\u00f1\u00d1]
Signo = [!@#$S.-]
Digito = [0123456789]
Comment = {TraditionalComment} | {EndOfLineComment}
LineTerminator = \r|\n|\r\n
 TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment     = "//"({Letra}|" "|{Signo}|{Digito})* {LineTerminator}?

%%
//Reglas Lexicas
<YYINITIAL>{    
        "nombre"                                  {return new Symbol(SimbolosLenguaje.nombre, yycolumn,yyline,yytext());}
        ("version"|"versión")                                  {return new Symbol(SimbolosLenguaje.version, yycolumn,yyline,yytext());}
"autor"                                  {return new Symbol(SimbolosLenguaje.autor, yycolumn,yyline,yytext());}
"lanzamiento"                                  {return new Symbol(SimbolosLenguaje.lanzamiento, yycolumn,yyline,yytext());}
("extension"|"extensión")                                  {return new Symbol(SimbolosLenguaje.extension, yycolumn,yyline,yytext());}
"%%"                                  {return new Symbol(SimbolosLenguaje.separador, yycolumn,yyline,yytext());}
";"                                  {return new Symbol(SimbolosLenguaje.puntoYComa, yycolumn,yyline,yytext());}
"*"                                  {return new Symbol(SimbolosLenguaje.asterisco, yycolumn,yyline,yytext());}
"?"                                  {return new Symbol(SimbolosLenguaje.qMark, yycolumn,yyline,yytext());}
"+"                                  {return new Symbol(SimbolosLenguaje.suma, yycolumn,yyline,yytext());}
"|"                                  {return new Symbol(SimbolosLenguaje.op, yycolumn,yyline,yytext());}
"[0-9]"                                  {return new Symbol(SimbolosLenguaje.numeros, yycolumn,yyline,yytext());}
"[a-z]"                                  {return new Symbol(SimbolosLenguaje.letras, yycolumn,yyline,yytext());}
"\\n"                                  {return new Symbol(SimbolosLenguaje.saltoDeLinea, yycolumn,yyline,yytext());}
"\\t"                                  {return new Symbol(SimbolosLenguaje.tab, yycolumn,yyline,yytext());}
"\\b"                                  {return new Symbol(SimbolosLenguaje.blanco, yycolumn,yyline,yytext());}
"\""|"“"|"”"                                  {return new Symbol(SimbolosLenguaje.comillas, yycolumn,yyline,yytext());}
"("                                  {return new Symbol(SimbolosLenguaje.parentesisA, yycolumn,yyline,yytext());}
")"                                  {return new Symbol(SimbolosLenguaje.parentesisC, yycolumn,yyline,yytext());}
"["                                  {return new Symbol(SimbolosLenguaje.corcheteA, yycolumn,yyline,yytext());}
"]"                                  {return new Symbol(SimbolosLenguaje.corcheteC, yycolumn,yyline,yytext());}
"="                                  {return new Symbol(SimbolosLenguaje.igual, yycolumn,yyline,yytext());}
"&"                                  {return new Symbol(SimbolosLenguaje.ampersand, yycolumn,yyline,yytext());}
","                                  {return new Symbol(SimbolosLenguaje.coma, yycolumn,yyline,yytext());}
"terminal"                            {return new Symbol(SimbolosLenguaje.sTerminal, yycolumn,yyline,yytext());}
"no"                                  {return new Symbol(SimbolosLenguaje.no, yycolumn,yyline,yytext());}
":"                                  {return new Symbol(SimbolosLenguaje.dosPuntos, yycolumn,yyline,yytext());}
"{"                                  {return new Symbol(SimbolosLenguaje.llaveA, yycolumn,yyline,yytext());}
"}"                                  {return new Symbol(SimbolosLenguaje.llaveC, yycolumn,yyline,yytext());}


        ({Digito})+                                     {return new Symbol(SimbolosLenguaje.Entero, yycolumn,yyline,yytext());}
(({Digito})+".")*({Digito})+                           {return new Symbol(SimbolosLenguaje.NumeroVersion, yycolumn,yyline,yytext());}      
       ({Letra}|"_"|{Signo}|{Digito})+        {return new Symbol(SimbolosLenguaje.Cadena, yycolumn,yyline,yytext()); }

        ("{"[^}]+"}"(" ")*";")                 {return new Symbol(SimbolosLenguaje.Codigo, yycolumn,yyline,yytext()); }
        ("%%%"[^%]+"%%%")                 {return new Symbol(SimbolosLenguaje.Fuente, yycolumn,yyline,yytext()); }
                {Comment}                      {}
                         [ \t\r\f\n\b]                {}
        .                                            {return new Symbol(SimbolosLenguaje.Anything,yycolumn,yyline,yytext());}

}


