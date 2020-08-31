/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

/**
 *
 * @author sergio
 */
public class SimbolosAFD {

    private boolean numeros;
    private boolean letras;
    private String cadena;
    private Integer numero;
    private boolean terminal;

    public SimbolosAFD(int numero, boolean numeros, boolean letras, String cadena) {
        
        this.numero = numero;
        this.numeros = numeros;
        this.letras = letras;
        this.cadena = cadena;

    }
    
        public SimbolosAFD(int numero, boolean terminal) {
        this.numero = numero;
        this.terminal=terminal;

    }

    @Override
    public String toString() {
        String str="";
        if (terminal) {
            str+="TERMINAL";
        }
        if (numeros) {
            str="NUMEROS";
        }
        if (letras) {
            str+="LETRAS";
        }
        if (cadena!=null) {
            str+=cadena;
        }
             
        return str;
    }

    public boolean isNumeros() {
        return numeros;
    }

    public boolean isLetras() {
        return letras;
    }

    public String getCadena() {
        return cadena;
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean isTerminal() {
        return terminal;
    }
    
    
}
