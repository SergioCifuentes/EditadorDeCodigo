    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TablaDeSiguientes {
    private ArrayList<ArrayList<Integer>> table ; 
    
    public TablaDeSiguientes(EstadoAVL raiz,int numero){
        table=new ArrayList<>();
        for (int i = 0; i < numero; i++) {
            table.add(new ArrayList<>());
        }
        
    }
    public void obtenerSiguientes(){
        
    }

    public ArrayList<ArrayList<Integer>> getTable() {
        return table;
    }
    
}
