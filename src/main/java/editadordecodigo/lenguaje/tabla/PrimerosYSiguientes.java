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
public class PrimerosYSiguientes {

    private ArrayList<Tupla> tabla;

    public PrimerosYSiguientes() {
        tabla = new ArrayList<>();

    }

    protected void armarTabla(ArrayList<Simbolo> simbolos, ArrayList<Produccion> producciones) {
        for (int i = 0; i < simbolos.size(); i++) {
            if (!simbolos.get(i).isTerminal()) {

                tabla.add(new Tupla(simbolos.get(i).getNombre(), isVacio(simbolos.get(i).getNombre(), producciones),
                        encontrarPrimeros(simbolos.get(i).getNombre(), producciones)));

            }
        }
        for (int i = 0; i < tabla.size(); i++) {
            System.out.println(i + " " + tabla.get(i).id + " " + tabla.get(i).vacio + " ");
            for (int j = 0; j < tabla.get(i).primeros.size(); j++) {
                System.out.print(tabla.get(i).primeros.get(j).getNombre() + " ");
            }
            System.out.println("");
        }
    }

    private ArrayList<Simbolo> encontrarPrimeros(String simbolo, ArrayList<Produccion> producciones) {
        ArrayList<Simbolo> primeros = new ArrayList<Simbolo>();
        for (int i = 0; i < producciones.size(); i++) {

            if (producciones.get(i).getNoTerminal().getNombre().equals(simbolo)) {
//Produccion es vacio
System.out.println("EM "+simbolo+"  "+producciones.get(i).getProducciones().size());
                if (producciones.get(i).getProducciones() == null || producciones.get(i).getProducciones().isEmpty()) {
                    agregarSinDuplicar(primeros, Simbolo.VACIO);
                    //Si es terminal
                } else if (producciones.get(i).getProducciones().get(0).isTerminal()) {
                    agregarSinDuplicar(primeros, producciones.get(i).getProducciones().get(0));
                    //si Es Vacio
                } else if (isVacio(producciones.get(i).getProducciones().get(0).getNombre(), producciones)) {
                    ArrayList<Simbolo> primerosAMeter = new ArrayList<>();
                    boolean todosVacios = true;
                    System.out.println("EMPEZAR CICLO PARA "+simbolo+"  "+producciones.get(i).getProducciones().size());
                    //Ciclo de las producciones Siguientes
                    for (int j = 0; j < producciones.get(i).getProducciones().size(); j++) {
                        //Si Es Terminal
                        if (producciones.get(i).getProducciones().get(j).isTerminal()) {
                            System.out.println("ES TERMINAL "+producciones.get(i).getProducciones().get(j).getNombre()+" para "+simbolo);
                            agregarSinDuplicar(primerosAMeter, producciones.get(i).getProducciones().get(j));
                            todosVacios = false;
                            break;
                         //Si es igual al simbolo evaluado
                        } else if (producciones.get(i).getProducciones().get(0).getNombre() == simbolo) {
                            if (!isVacio(producciones.get(i).getProducciones().get(j).getNombre(), producciones)) {
                                todosVacios = false;
                             break;
                            }
                        } else if (!isVacio(producciones.get(i).getProducciones().get(j).getNombre(), producciones)) {
                            
                            ArrayList<Simbolo> primerosDelSiguiente = encontrarPrimeros(producciones.get(i).getProducciones().get(j).getNombre(), producciones);
                            for (int k = 0; k < primerosDelSiguiente.size(); k++) {
                                agregarSinDuplicar(primerosAMeter, primerosDelSiguiente.get(k));
                            }
                            todosVacios = false;
                            break;
                        } else {
                            
                            ArrayList<Simbolo> primerosDelSiguiente = encontrarPrimeros(producciones.get(i).getProducciones().get(j).getNombre(), producciones);
                            System.out.println("PARA SIMBOLO "+simbolo+"ES VACIO "+producciones.get(i).getProducciones().get(j).getNombre()+" "+primerosDelSiguiente.size());
                            for (int k = 0; k < primerosDelSiguiente.size(); k++) {
                                agregarSinDuplicar(primerosAMeter, primerosDelSiguiente.get(k));
                            }
                           
                        }

                    }
                    if (!todosVacios) {
                        System.out.println("VACIOS");
                        primerosAMeter.remove(Simbolo.VACIO);
                    }
                    for (int j = 0; j < primerosAMeter.size(); j++) {
                        agregarSinDuplicar(primeros, primerosAMeter.get(j));
                    }
                } else {
                    if (producciones.get(i).getProducciones().get(0).getNombre() != simbolo) {
                        ArrayList<Simbolo> primerosDelSiguiente = encontrarPrimeros(producciones.get(i).getProducciones().get(0).getNombre(), producciones);
                        for (int j = 0; j < primerosDelSiguiente.size(); j++) {
                            agregarSinDuplicar(primeros, primerosDelSiguiente.get(j));
                        }
                    }

                }
            }
        }
        return primeros;
    }

    private void agregarSinDuplicar(ArrayList<Simbolo> sim, Simbolo sim2) {
        boolean existe = false;
        for (int j = 0; j < sim.size(); j++) {
            if (sim.get(j).getNombre().equals(sim2.getNombre())) {
                existe = true;
            }
        }
        if (!existe) {
            sim.add(sim2);
        }
    }

    private boolean isVacio(String simbolo, ArrayList<Produccion> producciones) {
        for (int i = 0; i < producciones.size(); i++) {
            if (producciones.get(i).getNoTerminal().getNombre().equals(simbolo)) {
                
                if (producciones.get(i).getProducciones() == null || producciones.get(i).getProducciones().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> encontrarSiguientes() {
        ArrayList<String> siguientes = new ArrayList<>();
        return siguientes;
    }

    public class Tupla {

        private String id;
        private boolean vacio;
        private ArrayList<Simbolo> primeros;
        private ArrayList<Simbolo> siguientes;

        public Tupla(String id, boolean vacio, ArrayList<Simbolo> primeros) {
            this.id = id;
            this.vacio = vacio;
            this.primeros = primeros;
        }

        public void setSiguientes(ArrayList<Simbolo> siguientes) {
            this.siguientes = siguientes;
        }

    }
}
