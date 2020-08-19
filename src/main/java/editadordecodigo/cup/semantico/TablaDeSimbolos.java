/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.cup.semantico;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TablaDeSimbolos {

    private ArrayList<Objeto> objetos;
    public static final int TIPO_SIMBOLO_TERMINAL = 1;
    public static final int TIPO_SIMBOLO_NO_TERMINAL = 2;

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

    public void InsertarValor(String id, int tipo, Object valor) {
        Objeto ob = (Objeto) obtenerExistencia(id, tipo);
        ob.setValor(valor);
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
