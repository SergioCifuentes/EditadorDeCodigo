/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ConstructorDeTabla {
 private ArrayList<Simbolo> simbolos;
 private ArrayList<Produccion> producciones;
 private PrimerosYSiguientes primerosYSiguientes;
 
    public ConstructorDeTabla(ArrayList<Simbolo> simbolos, ArrayList<Produccion> producciones) {
        
        this.simbolos = simbolos;
        this.producciones = producciones;
        System.out.println("SIMBOLOS "+simbolos.size());
        System.out.println("PRODS "+this.producciones.size());
        primerosYSiguientes= new PrimerosYSiguientes();
        primerosYSiguientes.armarTabla(simbolos, producciones);
    }
 
    
    
    
}
