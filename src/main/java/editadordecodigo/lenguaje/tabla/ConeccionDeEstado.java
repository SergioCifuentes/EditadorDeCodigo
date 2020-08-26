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
public class ConeccionDeEstado {
    private Simbolo simbolo;
    private Estado estadoFinal;
    private ArrayList<ProduccionEstado> produccionesIniciales= new ArrayList<>();
    public ConeccionDeEstado(Simbolo simbolo, Estado estadoFinal) {
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
    }
    public String toString(){
        return simbolo.getNombre()+" ==>> "+estadoFinal.getNumero();    
    }
    public ConeccionDeEstado(Simbolo simbolo, ArrayList<ProduccionEstado> produccionesIniciales) {
        this.simbolo = simbolo;
        this.produccionesIniciales = produccionesIniciales;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public ArrayList<ProduccionEstado> getProduccionesIniciales() {
        return produccionesIniciales;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }
    
    
    
}
