/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador.analisadorSintactico;

import editadordecodigo.lenguaje.afd.Token;
import editadordecodigo.lenguaje.tabla.Acciones.Accion;
import editadordecodigo.lenguaje.tabla.Acciones.Aceptacion;
import editadordecodigo.lenguaje.tabla.Acciones.GoTo;
import editadordecodigo.lenguaje.tabla.Acciones.Reduce;
import editadordecodigo.lenguaje.tabla.Acciones.Shift;
import editadordecodigo.lenguaje.tabla.Produccion;
import editadordecodigo.lenguaje.tabla.Simbolo;
import editadordecodigo.lenguaje.tabla.TablaLR;
import editadordecodigo.ui.Principal;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Pila {

    private ArrayList<Integer> pila;
    private ArrayList<Simbolo> simbolosActuales;
    private TablaLR tablaLR;
    private ArrayList<String[]> tablaDePila;
    private Principal principal;
    private String entrada;

    public Pila(TablaLR tablaLR, Principal principal, String entradaCompleta) {
        this.tablaLR = tablaLR;
        entrada = entradaCompleta + "$";
        this.principal = principal;
        tablaDePila = new ArrayList<>();
        simbolosActuales = new ArrayList<>();
        pila = new ArrayList<>();
        pila.add(1);
    }

    public String obtenerEstados() {
        String str = "";
        for (int i = 0; i < pila.size(); i++) {
            str += pila.get(i) + " ";
        }
        return str;
    }

    public String obtenerSimbolosActuales() {
        String str = "";
        for (int i = 0; i < simbolosActuales.size(); i++) {
            str += simbolosActuales.get(i).getNombre()+" ";
        }
        return str;
    }

    public ArrayList<String[]> getTablaDePila() {
        return tablaDePila;
    }

    boolean ingresarSiguienteToken(Token token, String entradaResultante) {

        System.out.println("PILA");
        int estado = pila.get(pila.size() - 1);

        Accion siguiente;
        if (token == null) {
            siguiente = tablaLR.getAcciones()[estado - 1][obtenerColumna(Simbolo.COMODIN.getNombre())];
            token = new Token(tablaLR.getSimbolosExistentes().size(), false, false, "", "$",false);
        } else {
            System.out.println(obtenerColumna(token.getNombre()));
            siguiente = tablaLR.getAcciones()[estado - 1][obtenerColumna(token.getNombre())];
        }

        if (siguiente != null) {
            if (siguiente.getClass().equals(Aceptacion.class)) {
                System.out.println("ACEPTAR");
                String[] fila1 = {obtenerEstados(), obtenerSimbolosActuales(), " ", "Aceptar"};
                tablaDePila.add(fila1);
                return false;

            } else if (siguiente.getClass().equals(Shift.class)) {
                System.out.println("SHIFT");
                String[] fila1 = {obtenerEstados(), obtenerSimbolosActuales(), token.getNombre() + " " + entradaResultante, "Desplazar"};
                tablaDePila.add(fila1);
                pila.add(siguiente.getNumeroDeAccion());
                if (token == null) {
                    simbolosActuales.add(Simbolo.COMODIN);

                } else {
                    simbolosActuales.add(new Simbolo(token.getNombre(), null, false));
                    return true;
                }

            } else if (siguiente.getClass().equals(Reduce.class)) {
                System.out.println("REDUCE");
                int numeroDeReduce = siguiente.getNumeroDeAccion();
               
                Produccion pr = tablaLR.getProduccions().get(numeroDeReduce - 1);
                String[] fila1 = {obtenerEstados(), obtenerSimbolosActuales(), token.getNombre() + " " + entradaResultante, "Reducir Mediante " + pr.toString()};
                tablaDePila.add(fila1);
                reducir(pr);
                while (pila.size() > simbolosActuales.size()) {
                    pila.remove(pila.size() - 1);
                }
                //Go To
                estado = pila.get(pila.size() - 1);
                Accion accionGoTo = tablaLR.getAcciones()[estado - 1][obtenerColumna(simbolosActuales.get(simbolosActuales.size() - 1).getNombre())];
                if (accionGoTo.getClass().equals(GoTo.class)) {
                    pila.add(accionGoTo.getNumeroDeAccion());
                } else {
                    System.out.println("Error Go To");
                    return false;
                }

                return ingresarSiguienteToken(token, entradaResultante);

            }
        } else {
            return false;
        }
        System.out.println(tablaLR.getAcciones()[estado][obtenerColumna(token.getNombre())]);
        return true;
    }

    public void reducir(Produccion prod) {
        if (prod.getProducciones().size() <= simbolosActuales.size()) {
            int aux = simbolosActuales.size() - prod.getProducciones().size();
            for (int i = simbolosActuales.size() - 1; i >= aux; i--) {
                simbolosActuales.remove(i);
            }
            simbolosActuales.add(prod.getNoTerminal());
        } else {
            System.out.println("ERROR");;
        }

    }

    public void mostrarTabla() {
        System.out.println("TABLA");
        for (int i = 0; i < tablaDePila.size(); i++) {
            System.out.println(tablaDePila.get(i)[0] + "|" + tablaDePila.get(i)[1] + "|" + tablaDePila.get(i)[2] + "|" + tablaDePila.get(i)[3]);
        }
    }

    public int obtenerColumna(String nombre) {

        for (int i = 0; i < tablaLR.getSimbolosExistentes().size(); i++) {
            if (tablaLR.getSimbolosExistentes().get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return 0;
    }
}
