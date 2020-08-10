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
    public void cargarPrueba(){
        lenguajes.add(new Lenguaje("java", "11","Microsystems", "1996", ".java"));
        lenguajes.add(new Lenguaje("c++", "17","Bjarne Stroustrup", "1983", ".cc"));
        lenguajes.add(new Lenguaje("python", "3.8.3","Guido van Rossum", "1970", ".py"));
    }

    public ArrayList<Lenguaje> getLenguajes() {
        return lenguajes;
    }
}
