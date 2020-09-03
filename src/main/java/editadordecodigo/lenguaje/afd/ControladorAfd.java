/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ControladorAfd {

    private ArrayList<Expresion> expresiones;
    private EstadoAVL principal;
    private Integer numeroTerminal;
    private TablaDeSiguientes tablaDeSiguientes;
    private TablaDeTransicion tablaDeTransicion;
    private ArrayList<EstadoAVL> simbolos;

    public ControladorAfd(ArrayList<Expresion> expresiones, ArrayList<EstadoAVL> simbolos) {
        this.expresiones = expresiones;
        this.simbolos = simbolos;
        numeroTerminal = simbolos.size() + 1;

    }
    public EstadoAFD obtenerInicioAFD(){
        return tablaDeTransicion.getEstadosAFD().get(0);
    }
    public void generarAFD() {
        principal = genararPrincipal();
        encontrarDatosDeArbol();
        tablaDeSiguientes = new TablaDeSiguientes(principal, numeroTerminal);

        principal.obternerSiguientes(tablaDeSiguientes);
        EstadoAFD estadoAFD = new EstadoAFD(0, principal.getPrimeros());
        tablaDeTransicion = new TablaDeTransicion(simbolos,tablaDeSiguientes,estadoAFD, expresiones);
        tablaDeTransicion.construirTabla();
    }

    private EstadoAVL genararPrincipal() {
        EstadoAVL estadoPrincipal = null;
        if (expresiones.size() > 0) {
            if (expresiones.size() > 1) {
                EstadoAVL aux = unirEstados(expresiones.get(0).getRaiz(), expresiones.get(1).getRaiz());
                for (int i = 2; i < expresiones.size(); i++) {

                    aux = unirEstados(aux, expresiones.get(i).getRaiz());
                }
                estadoPrincipal = aux;
            } else {
                estadoPrincipal = expresiones.get(0).getRaiz();

            }

            return agregarTerminal(estadoPrincipal);

        }

        return null;

    }

    private EstadoAVL unirEstados(EstadoAVL estado1, EstadoAVL estado2) {
        return new EstadoAVL(estado1, estado2, new Operacion(Operacion.O));

    }

    private EstadoAVL agregarTerminal(EstadoAVL estado1) {
        return new EstadoAVL(estado1, new EstadoAVL(numeroTerminal, true), new Operacion(Operacion.CONCATENACION));

    }

    private void encontrarDatosDeArbol() {
        //Anulables
        principal.verificarAnulable();
        //Primeros
        principal.verificarPrimeros();
        //Ultimos
        principal.verificarUltimos();
        
        
        
    }
}
