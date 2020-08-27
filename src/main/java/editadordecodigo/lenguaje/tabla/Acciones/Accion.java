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
public class Accion {
    protected  Integer numeroDeAccion;

    public Accion(Integer numeroDeAccion) {
        this.numeroDeAccion = numeroDeAccion;
    }

    public void setNumeroDeAccion(Integer numeroDeAccion) {
        this.numeroDeAccion = numeroDeAccion;
    }

    public Integer getNumeroDeAccion() {
        return numeroDeAccion;
    }
    
}
