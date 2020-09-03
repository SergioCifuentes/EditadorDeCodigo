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
    private Token sim;
    private boolean ignorado;
    private String letras = "abcdefghyjklmnñopqrstuvwxyz";
    private String numeros = "1234567890";

    public ConeccionesAFD(EstadoAFD estadoFinal, Token sim, String token) {
        ignorado = false;
        this.estadoFinal = estadoFinal;
        this.sim = sim;
    }
public String getTokenEsperado(){
    for (int i = 0; i < estadoFinal.getConeccionesAFDs().size(); i++) {
        if (estadoFinal.getConeccionesAFDs().get(i).getEstadoFinal().isTerminal()) {
            System.out.println("STRING ERROR "+estadoFinal.getConeccionesAFDs().get(i).getSim().toString());
            return estadoFinal.getConeccionesAFDs().get(i).getSim().toString();
        }
    }
    return null;
}
public String getPrimerTokenEsperado(){
    if (!estadoFinal.getConeccionesAFDs().isEmpty()) {
        return estadoFinal.getConeccionesAFDs().get(0).getSim().toString();
    }
    return "Letra";
}
    public Token getSim() {
        return sim;
    }

    public EstadoAFD getEstadoFinal() {
        return estadoFinal;
    }

    public boolean verificarMovimiento(String character) {
        if (character==null) {
            return false;
        }
        
        
        System.out.println("CARACTER:{"+character+"}SIM:{"+sim.getCadena()+"}");
        if (sim.isTerminal()) {
            if (character == null) {
                return true;
            }
        } else if (sim.isLetras()) {
            if (character.length() == 1) {
                if (letras.contains(character)) {
                    return true;
                }
            }

        } else if (sim.isNumeros()) {
            if (character.length() == 1) {
                if (character.length() == 1) {
                    if (numeros.contains(character)) {
                        return true;
                    }
                }
            }
        } else if (sim.getCadena().equals(character)) {
            return true;
        }
        return false;
    }

    
    @Override
    public String toString() {
        return "ConeccionesAFD{" + "estadoFinal=" + estadoFinal.getNumero() + ", sim=" + sim + '}';
    }

}
