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
public class ProduccionEstado {

    private Produccion produccion;
    private ArrayList<Simbolo> siguientes;
    private int posicion;

    public ProduccionEstado(Produccion produccion, int posicion) {
        siguientes = new ArrayList<>();

        this.produccion = produccion;
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        String str = "PE::" + produccion.getNoTerminal().getNombre() + "  POS: " + posicion + "  ";
        for (int i = 0; i < produccion.getProducciones().size(); i++) {
            str += produccion.getProducciones().get(i).getNombre() + " ";
        }
        str += "  SIG  ";
        if (siguientes != null) {

            for (int i = 0; i < siguientes.size(); i++) {
                str += siguientes.get(i).getNombre() + " ";
            }
        }
        return str;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public ArrayList<Simbolo> getSiguientes() {
        return siguientes;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setSiguientes(ArrayList<Simbolo> siguientes) {
        
        this.siguientes = siguientes;
    }

}
