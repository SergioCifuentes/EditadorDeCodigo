/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.cup.semantico;

import editadordecodigo.lenguaje.tabla.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TablaDeSimbolos {

    private ArrayList<Objeto> objetos;
    public static final int TIPO_SIMBOLO_TERMINAL = 1;
    public static final int TIPO_SIMBOLO_NO_TERMINAL = 2;

     public boolean verificarExistencia(String id,int tipo){
         if (tipo==TIPO_SIMBOLO_NO_TERMINAL||tipo==TIPO_SIMBOLO_TERMINAL) {
             return obtenerExistencia(id, TIPO_SIMBOLO_NO_TERMINAL)!=null||obtenerExistencia(id, TIPO_SIMBOLO_TERMINAL)!=null;
         }else{
        return obtenerExistencia(id, tipo)!=null;
         }
    }
     
     public boolean verificarExistenciaConTipo(String id,int tipo){
             return obtenerExistencia(id, tipo)!=null;
    }     
    public TablaDeSimbolos() {
        objetos = new ArrayList<>();
    }

    public Object obtenerExistencia(String id, int tipo) {
        int index = binarySearch(id);
        while (true) {

            if (objetos.size() >= index + 1 && objetos.get(index).getId().equals(id)) {
                if (objetos.get(index).getTipo() == tipo) {

                    return objetos.get(index);
                } else {
                    index++;
                }
            } else {
                return null;
            }

        }

    }

    public void insertarValor(String id, int tipo, Object valor) {
        System.out.println("Insertando "+id);
        Objeto ob = (Objeto) obtenerExistencia(id, tipo);
        ob.setValor(valor);
    }

    public ArrayList<Simbolo> getSimbolos(){
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getTipo()==TIPO_SIMBOLO_TERMINAL||objetos.get(i).getTipo()==TIPO_SIMBOLO_NO_TERMINAL) {
                simbolos.add((Simbolo)objetos.get(i).getValor());
            }
        }
        return simbolos;
    }
    public Object obtenerExistencia(String id) {

        int index = binarySearch(id);
        while (true) {
            if (objetos.size() >= index + 1 && objetos.get(index).getId().equals(id)) {

                return objetos.get(index);

            } else {
                return null;
            }

        }

    }
    public Object getValor(String id){
        Objeto ob = (Objeto) obtenerExistencia(id);
        return ob.getValor();
    }
    
    public Integer obtenerTipo(String id) {
        int index = binarySearch(id);
        if (objetos.size() >= index + 1 && objetos.get(index).getId().equals(id)) {
            return objetos.get(index).getTipo();
        }
        return null;
    }


    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void eliminar(String id, int tipo) {
        int index = binarySearch(id);
        if (index < objetos.size()) {
            if (objetos.get(index).getId().equals(id) && objetos.get(index).getTipo() == tipo) {
                objetos.remove(index);
            }
        }

    }

    public void insertObject(String id, int tipo, Object valObject) {
        int index = binarySearch(id);
        Objeto ob = new Objeto(id, tipo, valObject);
        System.out.println(id + "====insertar en :===" + index);
        objetos.add(index, ob);
    }

    private int binarySearch(String id) {

        int low = 0;
        int high = objetos.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midVal = objetos.get(mid).getId();
            int cmp = midVal.compareTo(id);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                while (mid > 0 && objetos.get(mid - 1).getId().equals(id)) {
                    mid--;
                }
                return mid;
            }
        }
        return low;
    }

}
