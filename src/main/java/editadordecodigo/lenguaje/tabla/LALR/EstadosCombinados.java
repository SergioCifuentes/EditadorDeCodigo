/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla.LALR;

import editadordecodigo.lenguaje.tabla.Estado;
import editadordecodigo.lenguaje.tabla.TablaLR;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class EstadosCombinados {
    private int numeroCombinacion;
    private ArrayList<Estado> estados;
    private ArrayList<EstadosCombinados> dependientes;
    private int posibilidad;
    private ReduccionLALR reduccionLALR;
    private static final int POSIBILIDAD_NO=1;
    private static final int POSIBILIDAD_SI=2;
    private static final int POSIBILIDAD_DEPENDE=3;
    public EstadosCombinados(ArrayList<Estado> estados, int numero,ReduccionLALR reduccionLALR) {
        this.reduccionLALR=reduccionLALR;
        numeroCombinacion=numero;
        this.estados = estados;
    }
    
    
    public boolean verificarCombinacion(TablaLR tabla){
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < estados.size(); j++) {
                int pos=verificarColisiones(estados.get(i), estados.get(j), tabla);
                if (pos==POSIBILIDAD_SI) {
                    reduccionLALR.combinarEstados(estados.get(i), estados.get(j));
                }else if(){
                    
                }else{
                    
                }
            }
        }
    }

    public int verificarColisiones(Estado estado1,Estado estado2,TablaLR tabla){
        int posibilidad =POSIBILIDAD_SI;
        for (int i = 0; i < tabla.getAcciones()[0].length; i++) {
            if (tabla.getAcciones()[estado1.getNumero()-1][i].getClass().equals(tabla.getAcciones()[estado2.getNumero()-1][i].getClass())
                    &&tabla.getAcciones()[estado1.getNumero()-1][i].getNumeroDeAccion()==tabla.getAcciones()[estado2.getNumero()-1][i].getNumeroDeAccion()) {
                
                
            }else if(tabla.getAcciones()[estado1.getNumero()-1][i].getClass().equals(tabla.getAcciones()[estado2.getNumero()-1][i].getClass())){
                if (reduccionLALR.verificarNumerosEnColision(estado1.getNumero(), estado2.getNumero())) {
                    posibilidad=POSIBILIDAD_DEPENDE;
                    
                }else{
                    return POSIBILIDAD_NO;
                }
                
            }else{
                return POSIBILIDAD_NO;
            }
        }
       return posibilidad;
    }
    
        public boolean  verificarNumerosEnColision(int numeroEstado1,int numeroEstado2){
            boolean existe1=false;
            boolean existe2=false;
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getNumero()==numeroEstado1) {
                existe1=true;
            }else if (estados.get(i).getNumero()==numeroEstado2) {
                existe2=true;
            }
        }
        return existe1&&existe2;
    }
}
