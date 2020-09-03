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
        System.out.println("RAIZES");
        for (int i = 0; i < raizes.size(); i++) {
            System.out.println(i+" "+raizes.get(i));
        }
        expresiones=raizes;
        this.lenguaje = lenguaje;
        
        constructorDeEstados= new ConstructorDeEstados(simbolos, produccions);
        if (constructorDeEstados.getTablaLR()!=null) {
            
            this.lenguaje.setTablaLR(constructorDeEstados.getTablaLR());
        }
        System.out.println("SSSSSSSSSSSSSSSSIIIIIIIIIIIIIIIIIMMMMMMMMMMMMMMMMMMM");
        System.out.println(sim.size());
        controladorAfd = new ControladorAfd(expresiones, sim);
        controladorAfd.generarAFD();
        this.lenguaje.setEstadoInical(controladorAfd.obtenerInicioAFD());
        //System.out.println(lenguaje);
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }
    
    
    
}
