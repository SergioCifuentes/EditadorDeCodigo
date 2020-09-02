/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ControlLenguajes {
    private ArrayList<Lenguaje> lenguajes;

    public ControlLenguajes() {
        lenguajes= new ArrayList<>();
    }
    

    public ArrayList<Lenguaje> getLenguajes() {
        return lenguajes;
    }
    
    public void addLenguaje(Lenguaje lenguaje){
        lenguajes.add(lenguaje);
    }

    public void cargarPrueba() {
      // lenguajes.add(new Lenguaje("Java", "1", "Juan", "1235", ".java"));
       //lenguajes.add(new Lenguaje("C++", "2", "Jose", "1235", ".cc"));
    }
}
