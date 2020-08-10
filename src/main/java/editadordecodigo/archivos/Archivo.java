/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.archivos;

import editadordecodigo.lenguaje.Lenguaje;
import java.io.File;

/**
 *
 * @author sergio
 */
public class Archivo {
    private String nombre;
    private Lenguaje lenguaje;
    private File ubicacion;


    public Archivo(String nombre, Lenguaje lenguaje, File ubicacion) {
        this.nombre = nombre;
        this.lenguaje = lenguaje;
        this.ubicacion = ubicacion;
    }

    
    public String getNombre() {
        return nombre;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public File getUbicacion() {
        return ubicacion;
    }
    
    
}
