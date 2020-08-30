/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

/**
 *
 * @author sergio
 */
public class Operacion {
    public static final int CONCATENACION =1;
    public static final int O =2;
    public static final int UNA_O_MAS_VECES =3;
    public static final int CERO_O_MAS_VECES =4;
    public static final int VENIR_O_NO =5;
    public  int tipo;

    public Operacion(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
    
    
}
