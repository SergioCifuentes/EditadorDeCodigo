/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador;

import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.ui.Principal;

/**
 *
 * @author sergio
 */
public class Compilador {
    private Lenguaje lenguaje;
    private String code;
    private Principal principal;
    private int columnaActual=0;
    private int indiceFinal;
    public Compilador(Lenguaje lenguaje, String code,Principal principal) {
        this.lenguaje = lenguaje;
        this.code = code;
        this.principal=principal;
    }
    public void compilar(){
        indiceFinal=0;
        AnalizadorLexico al = new AnalizadorLexico(lenguaje.getEstadoInical(),this);
        AnalizadorSintactico as = new AnalizadorSintactico(al, lenguaje,principal);
        as.start();
        
    }
    public void regresarindiceFinal(){
        indiceFinal--;
    }
    public String obtenerSiguienteCaracter(){
        if (columnaActual!=0) {
            columnaActual++;
        }
        System.out.println("Code "+code);
        System.out.println("Indeice "+indiceFinal );
        System.out.println("Len "+code.length());
        
        if (code.length()<=indiceFinal) {
            return null;
        }
        indiceFinal++;
        System.out.println(String.valueOf(code.charAt(indiceFinal-1)));
        return String.valueOf(code.charAt(indiceFinal-1));
    }

    public void resetColumanActual(){
        columnaActual=0;
    }

    public int getColumnaActual() {
        return columnaActual;
    }
    
    public Principal getPrincipal() {
        return principal;
    }
    
}
