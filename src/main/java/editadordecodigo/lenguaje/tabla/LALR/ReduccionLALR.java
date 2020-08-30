/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla.LALR;

import editadordecodigo.lenguaje.tabla.Acciones.GoTo;
import editadordecodigo.lenguaje.tabla.Acciones.Reduce;
import editadordecodigo.lenguaje.tabla.Acciones.Shift;
import editadordecodigo.lenguaje.tabla.Estado;
import editadordecodigo.lenguaje.tabla.ProduccionEstado;
import editadordecodigo.lenguaje.tabla.TablaLR;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ReduccionLALR {
private ArrayList<Estado> estados;
private TablaLR tabla;
private ArrayList<EstadosCombinados> estadosCom;

    public TablaLR intentarReducir(TablaLR tabla, ArrayList<Estado> estados) {
        this.estados=estados;
        this.tabla=tabla;
        estadosCom= new ArrayList<>();
        combinarEstados();
        
        
    }
    public void combinarEstados(){
        ArrayList<Integer> numeroYaCombinados= new ArrayList<>();
        for (int i = 0; i < estados.size(); i++) {
            ArrayList<Estado> estadosAux= new ArrayList<>();
            if (i!=1) {
                
                estadosAux.add(estados.get(i));
               for (int j = i; j < estados.size(); j++) {
                if (j!=1&&!numeroYaCombinados.contains(j)) {
                    if (verificarEstadosIguales(estados.get(i), estados.get(j))) {
                        estadosAux.add(estados.get(j));
                    }
                }
            } 
            }
            if (estadosAux.size()>1) {
                estadosCom.add(new EstadosCombinados(estados,i+1,this));
            }
        }
}
    public boolean  verificarNumerosEnColision(int numeroEstado1,int numeroEstado2){
        for (int i = 0; i < estadosCom.size(); i++) {
            if (estadosCom.get(i).verificarNumerosEnColision(numeroEstado1, numeroEstado2)) {
                return true;
            }
        }
        return false;
    }
    public void combinarEstados(Estado estado1,Estado estado2){
        int numero;
        if (estado1.getNumero()<estado2.getNumero()) {
            numero=estado1.getNumero();
        }else{
            numero=estado2.getNumero();
        }
        ArrayList<ProduccionEstado> nuevasProduccionEstado= new ArrayList<>();
        for (int i = 0; i < estado1.getProduciones().size(); i++) {
            ProduccionEstado pe = new ProduccionEstado(estado1.getProduciones().get(i).getProduccion(), estado1.getProduciones().get(i).getPosicion());
                   for (int j = 0; j < estado2.getProduciones().size(); j++) {
                       if (estado1.getProduciones().get(i).equals(estado2.getProduciones().get(j))
                            && estado1.getProduciones().get(i).getPosicion()==estado2.getProduciones().get(j).getPosicion()) {
                        pe.setSiguientes(estado1.getProduciones().get(i).getSiguientes(), estado2.getProduciones().get(j).getSiguientes());
                        nuevasProduccionEstado.add(pe);
                    }
            }
        }
        Estado nuevoEstado= new Estado(numero, nuevasProduccionEstado);
        estados.remove(estado1);
        estados.remove(estado2);
        estados.add(nuevoEstado);
        cambiarNumerosEntabla(estado1.getNumero(),estado2.getNumero(),numero);
        
    }
    public void cambiarNumerosEntabla(int numero1,int numero2, int numerofinal){
        for (int i = 0; i < tabla.getAcciones().length; i++) {
            for (int j = 0; j < tabla.getAcciones()[i].length; j++) {
                if (tabla.getAcciones()[i][j].getClass()==GoTo.class||tabla.getAcciones()[i][j].getClass()==Shift.class||tabla.getAcciones()[i][j].getClass()==Reduce.class) {
                    if (tabla.getAcciones()[i][j].getNumeroDeAccion()==numero1||tabla.getAcciones()[i][j].getNumeroDeAccion()==numero2) {
                        tabla.getAcciones()[i][j].setNumeroDeAccion(numerofinal);
                    }
                    
                }
            }
        }
    }
    
    public void verificarColisiones(){
        for (int i = 0; i < estadosCom.size(); i++) {
            
        }
        
        
    }
    public boolean verificarEstadosIguales(Estado estado1, Estado estado2) {
        if (estado1.getProduciones().size() == estado2.getProduciones().size()) {
            boolean existe = false;
            for (int i = 0; i < estado1.getProduciones().size(); i++) {
                existe = false;
                for (int j = 0; j < estado2.getProduciones().size(); j++) {
                    if (estado1.getProduciones().get(i).equals(estado2.getProduciones().get(j))
                            && estado1.getProduciones().get(i).getPosicion()==estado2.getProduciones().get(j).getPosicion()) {
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

}
