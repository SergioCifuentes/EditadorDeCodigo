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
public class TablaDeTransicion {

    private ArrayList<EstadoAVL> existentes;
    private ArrayList<EstadoAFD> estadosAFD;
    private TablaDeSiguientes tablaDeSiguientes;

    private ArrayList<EstadoAVL> estados;
    private ArrayList<ArrayList<Integer>> simplificacion;

    public TablaDeTransicion(ArrayList<EstadoAVL> estados, TablaDeSiguientes tablaDeSiguientes, EstadoAFD estadoInicio) {

        this.tablaDeSiguientes = tablaDeSiguientes;
        this.estados = estados;
        estadosAFD = new ArrayList<>();
        estadosAFD.add(estadoInicio);
        existentes = new ArrayList<>();

        obtenerExistentes(estados);
        

    }

    public void construirTabla() {
        for (int i = 0; i < estadosAFD.size(); i++) {
            derivar(estadosAFD.get(i));

        }
        for (int i = 0; i < estadosAFD.size(); i++) {
            System.out.println("==== "+estadosAFD.get(i).getNumero()+"====");
            System.out.println("TERMINAL "+estadosAFD.get(i).isTerminal());
            for (int j = 0; j <estadosAFD.get(i).getConeccionesAFDs().size(); j++) {
                System.out.println(estadosAFD.get(i).getConeccionesAFDs().get(j));
            }
        }

    }

    private void derivar(EstadoAFD estado) {

        for (int i = 0; i < estado.getNumerosDeSubConjunto().size(); i++) {

            if (estado.getNumerosDeSubConjunto().get(i) != estados.size() + 1) {
                ArrayList<Integer> num = tablaDeSiguientes.getTable().get(estado.getNumerosDeSubConjunto().get(i) - 1);
                EstadoAFD estadoExi = verificarExistente(num);
                System.out.println("FOR " + estado.getNumerosDeSubConjunto().get(i) + "NUM: ");
                for (int j = 0; j < num.size(); j++) {
                    System.out.print(num.get(j) + " ");
                }
                if (estadoExi == null) {
                    System.out.println("NULL;NUEVO");
                    EstadoAFD estadoNuevo = new EstadoAFD(estadosAFD.size() , num);
                    estadosAFD.add(estadoNuevo);
                    SimbolosAFD sim = crearSimbolo(estado.getNumerosDeSubConjunto().get(i));
                    ConeccionesAFD cafd = new ConeccionesAFD(estadoNuevo, sim);
                    estado.getConeccionesAFDs().add(cafd);

                } else {
                    System.out.println("EXISTE");
                    SimbolosAFD sim = crearSimbolo(estado.getNumerosDeSubConjunto().get(i));

                    ConeccionesAFD cafd = new ConeccionesAFD(estadoExi, sim);
                    estado.getConeccionesAFDs().add(cafd);

                }
            }else{
                estado.setTerminal(true);
            }

        }

    }

    private SimbolosAFD crearSimbolo(int numeroDeEstado) {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getNumero() == numeroDeEstado) {
                if (estados.get(i).isTerminal()) {
                    return new SimbolosAFD(numeroDeEstado, true);
                } else {
                    return new SimbolosAFD(numeroDeEstado, estados.get(i).isNumeros(), estados.get(i).isLetras(), estados.get(i).getCadena());
                }
            }

        }
        return null;
    }

    private EstadoAFD verificarExistente(ArrayList<Integer> num) {
        for (int i = 0; i < estadosAFD.size(); i++) {
            if (estadosAFD.get(i).getNumerosDeSubConjunto().size() == num.size() && estadosAFD.get(i).getNumerosDeSubConjunto().containsAll(num)) {
                return estadosAFD.get(i);
            }
        }
        return null;
    }

    private void obtenerExistentes(ArrayList<EstadoAVL> estados) {
        simplificacion = new ArrayList<>();
        boolean existente = false;
        for (int i = 0; i < estados.size(); i++) {
            ArrayList<Integer> numeros = new ArrayList<>();
            existente = false;
            for (int j = 0; j < existentes.size(); j++) {
                if (compararEstados(estados.get(i), existentes.get(j))) {
                    simplificacion.get(j).add(estados.get(i).getNumero());
                    existente = true;
                }
            }
            if (!existente) {
                existentes.add(estados.get(i));
                numeros.add(estados.get(i).getNumero());
                simplificacion.add(numeros);
            }
        }
    }

    private boolean compararEstados(EstadoAVL estado1, EstadoAVL estado2) {
        if (estado1.isLetras()) {
            if (estado2.isLetras()) {
                return true;
            } else {
                return false;
            }
        } else if (estado1.isNumeros()) {
            if (estado2.isNumeros()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (estado1.getCadena() != null && estado2.getCadena() != null) {
                if (estado1.getCadena().equals(estado2.getCadena())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
    }

}
