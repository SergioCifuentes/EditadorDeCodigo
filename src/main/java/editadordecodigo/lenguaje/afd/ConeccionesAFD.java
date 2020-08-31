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
public class ConeccionesAFD {
    private EstadoAFD estadoFinal;
    private SimbolosAFD sim;

    public ConeccionesAFD(EstadoAFD estadoFinal, SimbolosAFD sim) {
        this.estadoFinal = estadoFinal;
        this.sim = sim;
    }

    @Override
    public String toString() {
        return "ConeccionesAFD{" + "estadoFinal=" + estadoFinal.getNumero() + ", sim=" + sim + '}';
    }
    
    
    
}
