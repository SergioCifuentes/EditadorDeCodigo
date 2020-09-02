/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador.analisadorSintactico;

import editadordecodigo.compilador.AnalizadorLexico;
import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.lenguaje.afd.Token;
import editadordecodigo.ui.Principal;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AnalizadorSintactico {
    private AnalizadorLexico analizadorLexico;
    private Lenguaje lenguaje;
    private Principal principal;
    private String entrada;
    private Pila pila ;

    public AnalizadorSintactico(AnalizadorLexico analizadorLexico, Lenguaje lenguaje,Principal principal) {
        this.principal= principal;
        this.analizadorLexico = analizadorLexico;
        
        this.lenguaje = lenguaje;
    }
    public ArrayList<String[]> obtenerPila(){
        return pila.getTablaDePila();
    }
    
    public Token obtenerSiguienteToken(){
        return analizadorLexico.analizarSiguiente();
    }
    public void start(){
        obtenerEntrada();
         pila = new Pila(lenguaje.getTablaLR(),principal,entrada);
        while (true) {
            Token siguiente=obtenerSiguienteToken();
            obtenerEntrada();
            System.out.println("Recibiendo Token "+siguiente);
            
            if (siguiente==null) {
                boolean listo = pila.ingresarSiguienteToken(null,entrada);
                if (!listo) {
                    break;
                }
            }else{
                boolean listo = pila.ingresarSiguienteToken(siguiente,entrada);
                if (!listo) {
                    break;
                }
            }
            
        }
        pila.mostrarTabla();
    }

    public String getEntrada() {
        return entrada;
    }
    
    public void obtenerEntrada(){
        entrada=analizadorLexico.getEntradaCompleta();
    }
}
