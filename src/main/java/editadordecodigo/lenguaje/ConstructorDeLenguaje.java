/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje;

import editadordecodigo.cup.semantico.TablaDeSimbolos;
import editadordecodigo.lenguaje.afd.ControladorAfd;
import editadordecodigo.lenguaje.afd.EstadoAVL;
import editadordecodigo.lenguaje.afd.Expresion;
import editadordecodigo.lenguaje.tabla.ConstructorDeEstados;
import editadordecodigo.lenguaje.tabla.Produccion;
import editadordecodigo.lenguaje.tabla.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ConstructorDeLenguaje {
    private Lenguaje lenguaje;
    private ConstructorDeEstados constructorDeEstados;
    private ArrayList<Expresion> expresiones;
    private ControladorAfd controladorAfd;

    public ConstructorDeLenguaje(Lenguaje lenguaje,ArrayList<Simbolo> simbolos,ArrayList<Produccion> produccions,ArrayList<Expresion> raizes,ArrayList<EstadoAVL> sim) {
        expresiones=raizes;
        this.lenguaje = lenguaje;
        constructorDeEstados= new ConstructorDeEstados(simbolos, produccions);
        if (constructorDeEstados.getTablaLR()!=null) { 
            this.lenguaje.setTablaLR(constructorDeEstados.getTablaLR());
        }
        controladorAfd = new ControladorAfd(expresiones, sim);
        controladorAfd.generarAFD();
        this.lenguaje.setEstadoInical(controladorAfd.obtenerInicioAFD());
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }
    
    
    
}
