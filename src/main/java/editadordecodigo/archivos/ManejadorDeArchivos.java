/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.archivos;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ManejadorDeArchivos {
    private ArrayList<Archivo> archivos;

    public ManejadorDeArchivos() {
        archivos= new ArrayList<>();
    }
    public void addArchivo(Archivo archivo){
        archivos.add(archivo);
    }

    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }
    
}
