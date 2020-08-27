/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla.Acciones;

/**
 *
 * @author sergio
 */
public class Reduce extends Accion {

    public Reduce(Integer numeroDeAccion) {
        super(numeroDeAccion);
    }

    public String toString() {
        return "r" + numeroDeAccion;
    }
}
