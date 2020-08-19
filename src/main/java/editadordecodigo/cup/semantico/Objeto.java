/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.cup.semantico;

/**
 *
 * @author sergio
 */
public class Objeto {
    private String id;
    private int tipo;
    private Object valor;
    

    public Objeto(String id, int tipo, Object valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        
    }


    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    public String getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public Object getValor() {
        return valor;
    }
    
    
    
}
