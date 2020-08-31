/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class EstadoAFD {
    private int numero;
    private ArrayList<ConeccionesAFD> coneccionesAFDs;
    private ArrayList<Integer> numerosDeSubConjunto;
    private boolean terminal;

    public EstadoAFD(int numero, ArrayList<Integer> numerosDeSubConjunto) {
        terminal=false;
        this.numero = numero;
        this.numerosDeSubConjunto = numerosDeSubConjunto;
        coneccionesAFDs = new ArrayList<>();
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isTerminal() {
        return terminal;
    }
    
    public int getNumero() {
        return numero;
    }

    public ArrayList<ConeccionesAFD> getConeccionesAFDs() {
        return coneccionesAFDs;
    }

    public ArrayList<Integer> getNumerosDeSubConjunto() {
        return numerosDeSubConjunto;
    }
    
    
}
