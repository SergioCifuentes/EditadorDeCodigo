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
public class ConstructorDeEstados {

    private ArrayList<Simbolo> simbolos;
    private ArrayList<Produccion> producciones;
    private PrimerosYSiguientes primerosYSiguientes;
    private ArrayList<Estado> estados;
    private TablaLR tablaLR;

    public ConstructorDeEstados(ArrayList<Simbolo> simbolos, ArrayList<Produccion> producciones) {

        this.simbolos = simbolos;
        this.producciones = producciones;
        estados = new ArrayList<>();
        primerosYSiguientes = new PrimerosYSiguientes();
        primerosYSiguientes.armarTabla(simbolos, producciones);
        ArrayList<Simbolo> simInicio = new ArrayList<>();
        simInicio.add(producciones.get(0).getNoTerminal());
        simInicio.add(Simbolo.COMODIN);
        Produccion proInico = new Produccion(Simbolo.PRIMA, simInicio, null);
        ArrayList<ProduccionEstado> produccionEstados = new ArrayList<>();
        produccionEstados.add(new ProduccionEstado(proInico, 0));

        estados.add(cerradura(produccionEstados));
        for (int i = 0; i < estados.size(); i++) {
            estados.get(i).setConecciones(irA(estados.get(i)));
            for (int j = 0; j < estados.get(i).getConecciones().size(); j++) {
                estados.get(i).getConecciones().get(j).setEstadoFinal(cerradura(estados.get(i).getConecciones().get(j).getProduccionesIniciales()));
                Estado existente = regresarEstadoIgual(estados.get(i).getConecciones().get(j).getEstadoFinal());
                if (existente == null) {
                    estados.add(estados.get(i).getConecciones().get(j).getEstadoFinal());
                } else {
                    estados.get(i).getConecciones().get(j).setEstadoFinal(existente);
                }

            }
        }
        
        tablaLR= new TablaLR(estados, this.simbolos,this.producciones);
        
    }

    public TablaLR getTablaLR() {
        return tablaLR;
    }

    public Estado cerradura(ArrayList<ProduccionEstado> produccion) {
        ArrayList<ProduccionEstado> pe = new ArrayList<>();
        pe.addAll(produccion);

        for (int i = 0; i < pe.size(); i++) {
            System.out.println("ADERIVAR " + i + " " + pe.toString());
            if (pe.get(i).getProduccion().getProducciones().size() != 0 && pe.get(i).getProduccion().getProducciones().size() > pe.get(i).getPosicion()) {
                agregarSinRepetir(pe, derivar(pe.get(i)));
            }

        }
        Estado nueEstado = new Estado(estados.size() + 1, pe);

        return nueEstado;
    }

    public Estado regresarEstadoIgual(Estado estado) {
        for (int i = 0; i < estados.size(); i++) {
            if (verificarEstadosIguales(estado, estados.get(i))) {
                return estados.get(i);
            }
        }
        return null;
    }

    public boolean verificarEstadosIguales(Estado estado1, Estado estado2) {
        if (estado1.getProduciones().size() == estado2.getProduciones().size()) {
            boolean existe = false;
            for (int i = 0; i < estado1.getProduciones().size(); i++) {
                existe = false;
                for (int j = 0; j < estado2.getProduciones().size(); j++) {
                    if (estado1.getProduciones().get(i).toString().equals(estado2.getProduciones().get(j).toString())) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    return false;

                }
            }
            return true;
        } else {
            return false;
        }

    }

    public void agregarSinRepetir(ArrayList<ProduccionEstado> pe, ArrayList<ProduccionEstado> aMeter) {
        boolean existe = false;
        for (int i = 0; i < aMeter.size(); i++) {
            existe = false;
            for (int j = 0; j < pe.size(); j++) {

                if (pe.get(j).getProduccion().equals(aMeter.get(i).getProduccion())&&pe.get(j).getPosicion()==aMeter.get(i).getPosicion()) {

                    existe = true;
                    agregarSimbolosSinRepetir(pe.get(j).getSiguientes(), aMeter.get(i).getSiguientes());
                    break;
                }
            }
            if (!existe) {
                existe = false;
                pe.add(aMeter.get(i));
            }
        }
    }

    public static void agregarSimbolosSinRepetir(ArrayList<Simbolo> sim, ArrayList<Simbolo> aMeter) {
        boolean existe = false;
        for (int i = 0; i < aMeter.size(); i++) {
            existe = false;
            for (int j = 0; j < sim.size(); j++) {
                if (sim.get(j).equals(aMeter.get(i))) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                existe = false;
                sim.add(aMeter.get(i));
            }
        }
    }

    public ArrayList<ProduccionEstado> derivar(ProduccionEstado pe) {
        ArrayList<ProduccionEstado> producciones = new ArrayList<>();
        Simbolo simboloAEvaluar = pe.getProduccion().getProducciones().get(pe.getPosicion());
        ArrayList<Simbolo> siguientes = new ArrayList<>();
        siguientes.addAll(obtenerSiguientes(pe, pe.getPosicion()));
        for (int i = 0; i < this.producciones.size(); i++) {
            if (this.producciones.get(i).getNoTerminal().equals(simboloAEvaluar)) {
                ProduccionEstado nuevoPE = new ProduccionEstado(this.producciones.get(i), 0);
                nuevoPE.setSiguientes(siguientes);
                producciones.add(nuevoPE);
            }
        }
        return producciones;
    }

    public ArrayList<Simbolo> obtenerSiguientes(ProduccionEstado pe, int posicion) {
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        if (posicion + 1 == pe.getProduccion().getProducciones().size()) {
            return pe.getSiguientes();
        } else if (pe.getProduccion().getProducciones().get(posicion + 1).isTerminal()) {
            simbolos.add(pe.getProduccion().getProducciones().get(posicion + 1));
            return simbolos;
        } else if (primerosYSiguientes.esVacioDeTabla(pe.getProduccion().getProducciones().get(posicion + 1))) {
            agregarSimbolosSinRepetir(simbolos, primerosYSiguientes.obtenerPrimeros(pe.getProduccion().getProducciones().get(posicion + 1)));
            simbolos.remove(Simbolo.VACIO);
            simbolos.addAll(obtenerSiguientes(pe, posicion + 1));
            return simbolos;
        } else {
            return primerosYSiguientes.obtenerPrimeros(pe.getProduccion().getProducciones().get(posicion + 1));
        }
    }

    public ArrayList<ConeccionDeEstado> irA(Estado estado) {

        ArrayList<ConeccionDeEstado> con = new ArrayList<>();
        ArrayList<Simbolo> simbolosSiguientes = estado.conseguirSimbolosDelSiguienteEstado();
        System.out.println("IIIIIIIIIIIIIIIIIIRRRRRRAAAAAAAAa  " + simbolosSiguientes.size());
        ArrayList<ProduccionEstado> pe = new ArrayList<>();
        for (int i = 0; i < simbolosSiguientes.size(); i++) {
            System.out.println("SIMN " + i + " " + simbolosSiguientes.get(i).getNombre());
            pe= new ArrayList<>();
            for (int j = 0; j < estado.getProduciones().size(); j++) {
                if (estado.getProduciones().get(j).getProduccion().getProducciones().size() != 0 && estado.getProduciones().get(j).getProduccion().getProducciones().size() > estado.getProduciones().get(j).getPosicion()) {
                    if (simbolosSiguientes.get(i).equals(estado.getProduciones().get(j).getProduccion().getProducciones().get(estado.getProduciones().get(j).getPosicion()))) {
                        ProduccionEstado nuevope = new ProduccionEstado(estado.getProduciones().get(j).getProduccion(), estado.getProduciones().get(j).getPosicion()+1);
                        nuevope.setSiguientes(estado.getProduciones().get(j).getSiguientes());
                        pe.add(nuevope);
                    }
                }
            }
            con.add(new ConeccionDeEstado(simbolosSiguientes.get(i), pe));
        }
        return con;
    }

}
