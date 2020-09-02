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
public class Token {
    private String nombre;
    private boolean numeros;
    private boolean letras;
    private String cadena;
    private Integer numero;
    private boolean terminal;

    public Token(int numero, boolean numeros, boolean letras, String cadena,String nombre) {
        this.nombre=nombre;
        this.numero = numero;
        this.numeros = numeros;
        this.letras = letras;
        this.cadena = cadena;

    }

    public String getNombre() {
        return nombre;
    }
    
    
        public Token(int numero, boolean terminal) {
        this.numero = numero;
        this.terminal=terminal;

    }

    @Override
    public String toString() {
        
             
        return nombre;
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
