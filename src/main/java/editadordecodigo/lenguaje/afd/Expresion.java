/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sergio
 */
public class Expresion {
    private EstadoAVL raiz;
    private String nombre;
    private boolean amperson;
    private final static String AMPERSON="&AMPERSON&";
    private ArrayList<Integer> numrosDeEstado;

    public Expresion(EstadoAVL raiz, String nombre) {
        numrosDeEstado= new ArrayList<>();
        amperson=false;
        this.raiz = raiz;
        this.nombre = nombre;
        agregarNumerosSinRepetir(obtenerNumeros(raiz));
    }

    public Expresion(EstadoAVL raiz, boolean amperson) {
        numrosDeEstado= new ArrayList<>();
        nombre=AMPERSON;
        this.raiz = raiz;
        this.amperson = amperson;
        obtenerNumeros(raiz);
        agregarNumerosSinRepetir(obtenerNumeros(raiz));
    }
    public ArrayList<Integer> obtenerNumeros(EstadoAVL estado){
        ArrayList<Integer> numeros=new ArrayList<>();
        if (estado.getOperacion()!=null) {
            numeros.addAll(obtenerNumeros(estado.getEstado1()));
            if (estado.getEstado2()!=null) {
                numeros.addAll(obtenerNumeros(estado.getEstado2()));
            }
            return numeros;
        }else{
           return estado.getPrimeros();
        }
    }
    public void agregarNumerosSinRepetir(ArrayList<Integer> numeros){
        for (int i = 0; i < numeros.size(); i++) {
            if (!numrosDeEstado.contains(numeros.get(i))) {
                numrosDeEstado.add(numeros.get(i));
                Collections.sort(numrosDeEstado);
            }
        }
    }
    
    public ArrayList<Integer> getNumrosDeEstado() {
        return numrosDeEstado;
    }


    public EstadoAVL getRaiz() {
        return raiz;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAmperson() {
        return amperson;
    }
    public String toString(){
        return nombre+" : "+numrosDeEstado;
    }
}
