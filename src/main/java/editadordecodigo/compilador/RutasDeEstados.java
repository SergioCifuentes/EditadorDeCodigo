/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador;

import editadordecodigo.lenguaje.afd.ConeccionesAFD;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class RutasDeEstados {
    private ArrayList<ConeccionesAFD> conecciones;
    public RutasDeEstados() {
        
        conecciones= new ArrayList<>();
    }

    public RutasDeEstados(ArrayList<ConeccionesAFD> coneccionesAFDs,ConeccionesAFD coneccioneSiguiente) {
        
        conecciones= new  ArrayList<>();
        conecciones.addAll(coneccionesAFDs);
        conecciones.add(coneccioneSiguiente);
    }

   

    
    public ArrayList<ConeccionesAFD> getConecciones() {
        return conecciones;
    }
    public ArrayList<ConeccionesAFD> verificarSiguiente(String caracter){
        ConeccionesAFD conFinal = conecciones.get(conecciones.size()-1);
        ArrayList<ConeccionesAFD> con = new ArrayList<>();
        for (int i = 0; i < conFinal.getEstadoFinal().getConeccionesAFDs().size(); i++) {
            if (conFinal.getEstadoFinal().getConeccionesAFDs().get(i).verificarMovimiento(caracter)) {
                con.add(conFinal.getEstadoFinal().getConeccionesAFDs().get(i));
            }
        }
        return con;
    }
    
    public void regresarPaso(){
        if (!conecciones.isEmpty()) {
            conecciones.remove(conecciones.size()-1);
        }
        
    }
}
