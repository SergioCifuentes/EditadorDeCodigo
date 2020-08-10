/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje;

/**
 *
 * @author sergio
 */
public class Lenguaje {
    private String nombre;
    private String version;
    private String Autor;
    private String lanzamiento;
    private String Extension;       

    public Lenguaje(String nombre, String version, String Autor, String lanzamiento, String Extension) {
        this.nombre = nombre;
        this.version = version;
        this.Autor = Autor;
        this.lanzamiento = lanzamiento;
        this.Extension = Extension;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }

    public String getAutor() {
        return Autor;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public String getExtension() {
        return Extension;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
}
