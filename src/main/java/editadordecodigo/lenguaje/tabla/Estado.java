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
public class Estado {

    private int numero;
    private ArrayList<ProduccionEstado> produciones;
    private ArrayList<ConeccionDeEstado> conecciones;

    public Estado(int  numero, ArrayList<ProduccionEstado> produciones) {
        this.numero = numero;
        this.produciones = produciones;
        conecciones = new ArrayList<>();

    }

    public int getNumero() {
        return numero;
    }

    public String toString(){
        String str = "ESTADO : "+numero+"\n ===PROD===\n";
        for (int i = 0; i < produciones.size(); i++) {
            str+=produciones.get(i)+"\n";
        }
        str+="===CON===";
        for (int i = 0; i < conecciones.size(); i++) {
            str+=conecciones.get(i).toString();
        }
        return str;
    }
    
    public ArrayList<ProduccionEstado> getProduciones() {
        return produciones;
    }

    public ArrayList<Simbolo> conseguirSimbolosDelSiguienteEstado() {
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        
        for (int i = 0; i < produciones.size(); i++) {
          if (produciones.get(i).getProduccion().getProducciones().size() != 0 && produciones.get(i).getProduccion().getProducciones().size() > produciones.get(i).getPosicion()) {
               Simbolo sim =produciones.get(i).getProduccion().getProducciones().get(produciones.get(i).getPosicion());
                if (sim!=Simbolo.VACIO&&sim!=Simbolo.COMODIN) {
                    System.out.println("AMETER "+produciones.get(i).getProduccion().getProducciones().get(produciones.get(i).getPosicion()).getNombre());
                    simbolos.add(produciones.get(i).getProduccion().getProducciones().get(produciones.get(i).getPosicion()));
                }
                
            }
        }
        
        ArrayList<Simbolo> simbolosAux = new ArrayList<>();
        ConstructorDeEstados.agregarSimbolosSinRepetir(simbolosAux, simbolos);
    return simbolosAux;
}
public void setConecciones(ArrayList<ConeccionDeEstado> conecciones) {
        this.conecciones = conecciones;
    }

    public ArrayList<ConeccionDeEstado> getConecciones() {
        return conecciones;
    }
    
    
}
