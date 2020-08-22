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
public class Produccion {
    private Simbolo noTerminal;
    private ArrayList<Simbolo> producciones;
    private String code;

    public Produccion(Simbolo noTerminal, ArrayList<Simbolo> producciones, String code) {
        this.noTerminal = noTerminal;
        this.producciones = producciones;
        if (code.startsWith("{")) {
            code=code.substring(1, code.length());
            code=code.replaceFirst("(?s)"+"}"+"(?!.*?"+"}"+")", "");
            code=code.replaceFirst("(?s)"+";"+"(?!.*?"+";"+")", "");
        }
        this.code = code;
    }
    @Override
    public String toString(){
        String str =noTerminal.getNombre()+" -> ";
        for (int i = 0; i < producciones.size(); i++) {
            str+=producciones.get(i).getNombre()+":"+producciones.get(i).getId()+"-"+producciones.get(i).isTerminal()+" ";
        }
        str+="CODE:"+ code;
        return str;
    }

    public Simbolo getNoTerminal() {
        return noTerminal;
    }

    public ArrayList<Simbolo> getProducciones() {
        return producciones;
    }

    public String getCode() {
        return code;
    }
    
    
}
