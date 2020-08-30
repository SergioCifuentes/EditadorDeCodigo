/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla;

import editadordecodigo.lenguaje.tabla.Acciones.Accion;
import editadordecodigo.lenguaje.tabla.Acciones.GoTo;
import editadordecodigo.lenguaje.tabla.Acciones.Reduce;
import editadordecodigo.lenguaje.tabla.Acciones.Shift;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TablaLR {
    private Accion[][] acciones;
    private ArrayList<Simbolo> simbolosExistentes;
    private ArrayList<Produccion> produccions;
    private ArrayList<Estado> estado;
    
    public TablaLR(ArrayList<Estado> estados,ArrayList<Simbolo> simbolosExistentes,ArrayList<Produccion> produccions) {
        acciones= new Accion[estados.size()][simbolosExistentes.size()+1];
        this.simbolosExistentes=simbolosExistentes;
        this.estado=estados;
        ordenarSimbolosExistentes();
        this.produccions=produccions;
        agregarAcciones(estados);
        System.out.print(" "+"\t");
        for (int i = 0; i < this.simbolosExistentes.size(); i++) {
            System.out.print(" "+this.simbolosExistentes.get(i).getNombre()+"  ");
        }
        System.out.println("");
        for (int i = 0; i < acciones.length; i++) {
            System.out.print(i+"\t");
            for (int j = 0; j < acciones[i].length; j++) {
                if (acciones[i][j]!=null) {
                    System.out.print(" "+acciones[i][j]+" ");
                }else{
                    System.out.print(" -- ");
                }
            }
            System.out.println("");
        }

    }

    public ArrayList<Estado> getEstado() {
        return estado;
    }
    
    public Object[] obtenerSimbolosParaTabla(){
        Object[] objetos= new Object[simbolosExistentes.size()+1];
        objetos[0]="";
        for (int i = 0; i < simbolosExistentes.size(); i++) {
            objetos[i+1]=simbolosExistentes.get(i).getNombre();
        }
        return objetos;
    }

    public Accion[][] getAcciones() {
        return acciones;
    }
    
    public Object[][] obtenerDatos(){
        Object[][] objetos= new Object[estado.size()][simbolosExistentes.size()+1];
        
        for (int i = 0; i < estado.size(); i++) {
            objetos[i][0]=(i+1);
        }for (int i = 0; i < acciones.length; i++) {
            for (int j = 0; j < acciones[i].length; j++) {
                if (acciones[i][j]!=null) {
                    objetos[i][j+1]=acciones[i][j].toString();
                }
            }
        }
        return objetos;
    }
    public void agregarAcciones(ArrayList<Estado> estados){
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < estados.get(i).getConecciones().size(); j++) {
                Simbolo con=estados.get(i).getConecciones().get(j).getSimbolo();
                if (con.isTerminal()) {
                    acciones[i][obtenerIndiceSimbolo(con)]=new Shift(estados.get(i).getConecciones().get(j).getEstadoFinal().getNumero());
                }else{
                    acciones[i][obtenerIndiceSimbolo(con)]=new GoTo(estados.get(i).getConecciones().get(j).getEstadoFinal().getNumero());
                }
            }
            for (int j = 0; j < estados.get(i).getProduciones().size(); j++) {
                if (estados.get(i).getProduciones().get(j).esProduccionAcabada()) {
                    for (int k = 0; k < estados.get(i).getProduciones().get(j).getSiguientes().size(); k++) {
                        acciones[i][obtenerIndiceSimbolo(estados.get(i).getProduciones().get(j).getSiguientes().get(k))]=
                                new Reduce(obtenerIndiceProduccion(estados.get(i).getProduciones().get(j).getProduccion()));
                    }
                    
                }
            }
        }
    }
    public void ordenarSimbolosExistentes(){
        ArrayList<Simbolo> aux = new ArrayList<>();
        for (int i = 0; i < simbolosExistentes.size(); i++) {
            if (simbolosExistentes.get(i).isTerminal()) {
                aux.add(simbolosExistentes.get(i));
            }
        }
        aux.add(Simbolo.COMODIN);
        for (int i = 0; i < simbolosExistentes.size(); i++) {
            if (!simbolosExistentes.get(i).isTerminal()) {
                aux.add(simbolosExistentes.get(i));
            }
        }
        simbolosExistentes= aux;
    }
    public Integer obtenerIndiceSimbolo(Simbolo simbolo){
        for (int i = 0; i < simbolosExistentes.size(); i++) {
            if (simbolo.equals(simbolosExistentes.get(i))) {
                return i;
            }
        }
        return null;
    }
    
    public Integer obtenerIndiceProduccion(Produccion p){
        for (int i = 0; i < produccions.size(); i++) {
            if (p.equals(produccions.get(i))) {
                return i+1;
            }
        }
        return null;
    }
    
}
