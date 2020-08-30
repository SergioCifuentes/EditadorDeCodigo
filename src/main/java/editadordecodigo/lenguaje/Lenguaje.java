/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje;

import editadordecodigo.lenguaje.tabla.TablaLR;

/**
 *
 * @author sergio
 */
public class Lenguaje {
    private String nombre;
    private String version;
    private String autor;
    private String lanzamiento;
    private String extension;
    private TablaLR tablaLR;
         

    public Lenguaje(String nombre, String version, String Autor, String lanzamiento, String Extension) {
        this.nombre = nombre;
        this.version = version;
        this.autor = Autor;
        this.lanzamiento = lanzamiento;
        this.extension = Extension;
    }

    public TablaLR getTablaLR() {
        return tablaLR;
    }
    
    @Override
    public String toString(){
        return "Nombre: "+nombre+"  Version: "+version+"  aut: "+autor+"  lan: "+lanzamiento+"  Ex: "+extension;
    }

    public void setTablaLR(TablaLR tablaLR) {
        this.tablaLR = tablaLR;
    }

    public Lenguaje(Object[] info) {
        nombre=(String)info[0];
        if (info[1]!=null) {
            version=(String)info[1];
        }
        if (info[2]!=null) {
            autor=(String)info[2];
        }
        if (info[3]!=null) {
            lanzamiento=(String)info[3];
        }
        if (info[4]!=null) {
            extension=(String)info[4];
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }

    public String getAutor() {
        return autor;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public String getExtension() {
        return extension;
    }
    

    
}
