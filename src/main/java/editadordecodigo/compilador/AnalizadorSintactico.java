/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador;

import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.lenguaje.afd.Token;
import editadordecodigo.ui.Principal;

/**
 *
 * @author sergio
 */
public class AnalizadorSintactico {
    private AnalizadorLexico analizadorLexico;
    private Lenguaje lenguaje;
    private Principal principal;

    public AnalizadorSintactico(AnalizadorLexico analizadorLexico, Lenguaje lenguaje,Principal principal) {
        this.principal= principal;
        this.analizadorLexico = analizadorLexico;
        
        this.lenguaje = lenguaje;
    }
    
    public Token obtenerSiguienteToken(){
        return analizadorLexico.analizarSiguiente();
    }
    public void start(){
        while (true) {
            Token siguiente=obtenerSiguienteToken();
            System.out.println("Recibiendo Token "+siguiente);
            if (siguiente==null) {
                break;
            }
            
        }
    }
}
