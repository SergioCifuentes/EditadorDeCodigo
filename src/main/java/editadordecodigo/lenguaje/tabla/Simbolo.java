/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.tabla;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Simbolo {
    public  static final Simbolo VACIO=new Simbolo("vacio", "vacio", true);
    public  static final Simbolo COMODIN  =new Simbolo("comodin", "comodin", true);
    public  static final Simbolo PRIMA  =new Simbolo("prima", "prima", true);
    private String nombre;
    private String id;
    private boolean terminal;
    private Object valor;
    

    public Simbolo(String nombre, String id,boolean terminal) {
        this.nombre = nombre;
        this.id = id;
        this.terminal=terminal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public Object getValor() {
        return valor;
    }

    public void setId(String id) {
        this.id = id;
    }

 
    
}
