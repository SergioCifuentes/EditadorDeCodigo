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
public class Expresion {
    private EstadoAVL raiz;
    private String nombre;
    private boolean amperson;

    public Expresion(EstadoAVL raiz, String nombre) {
        this.raiz = raiz;
        this.nombre = nombre;
    }

    public Expresion(EstadoAVL raiz, boolean amperson) {
        this.raiz = raiz;
        this.amperson = amperson;
    }

    public EstadoAVL getRaiz() {
        return raiz;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAmperson() {
        return amperson;
    }
    
}
