/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje.afd;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sergio
 */
public class EstadoAVL {

    private EstadoAVL estado1;
    private EstadoAVL estado2;
    private Operacion operacion;
    private boolean numeros;
    private boolean letras;
    private String cadena;
    private Integer numero;
    private boolean terminal;
    private Boolean anulable;
    private ArrayList<Integer> primeros = new ArrayList<>();
    private ArrayList<Integer> ultimos = new ArrayList<>();

    public EstadoAVL(int numero, boolean numeros, boolean letras, String cadena) {
        primeros.add(numero);
        ultimos.add(numero);
        this.anulable = false;
        this.numero = numero;
        this.numeros = numeros;
        this.letras = letras;
        this.cadena = cadena;

    }

    public EstadoAVL(int numero, boolean terminal) {
        primeros.add(numero);
        ultimos.add(numero);
        anulable = false;
        this.terminal = terminal;
        this.numero = numero;
    }

    public boolean verificarAnulable() {
        if (anulable == null) {
            if (estado2 != null) {

                switch (operacion.tipo) {
                    case Operacion.CONCATENACION:

                        anulable = (estado1.verificarAnulable() & estado2.verificarAnulable());
                        return anulable;

                    case Operacion.O:
                        anulable = (estado1.verificarAnulable() | estado2.verificarAnulable());
                        return anulable;

                    default:
                        throw new AssertionError();
                }
            } else if (operacion != null) {
                switch (operacion.tipo) {
                    case Operacion.CERO_O_MAS_VECES:
                        estado1.verificarAnulable();
                        anulable = true;
                        return anulable;
                    case Operacion.UNA_O_MAS_VECES:

                        anulable = verificarAnulable();
                        return anulable;
                    case Operacion.VENIR_O_NO:
                        estado1.verificarAnulable();
                        anulable = true;
                        return anulable;
                    default:
                        throw new AssertionError();
                }
            } else {

                anulable = estado1.verificarAnulable();
                return anulable;
            }
        } else {
            return anulable;
        }
    }

    public ArrayList<Integer> verificarPrimeros() {
        if (primeros.isEmpty()) {
            if (estado2 != null) {

                switch (operacion.tipo) {
                    case Operacion.CONCATENACION:
                        if (estado1.anulable) {
                            primeros=agregarSinRepetir(estado1.verificarPrimeros(),estado2.verificarPrimeros());
                        } else {
                            primeros=estado1.verificarPrimeros();
                        }
                        return primeros;
                    case Operacion.O:
                        primeros=agregarSinRepetir(estado1.verificarPrimeros(),estado2.verificarPrimeros());
                        return primeros;
                    default:
                        throw new AssertionError();
                }
            } else if (operacion != null) {
                primeros=estado1.verificarPrimeros();
                return primeros;
            } else {
                primeros  = estado1.verificarPrimeros();
                return primeros;
            }
        } else {
            return primeros;
        }
    }
        public ArrayList<Integer> verificarUltimos() {
        if (ultimos.isEmpty()) {
            if (estado2 != null) {

                switch (operacion.tipo) {
                    case Operacion.CONCATENACION:
                        if (estado2.anulable) {
                            ultimos=agregarSinRepetir(estado1.verificarUltimos(),estado2.verificarUltimos());
                        } else {
                            ultimos=estado2.verificarPrimeros();
                        }
                        return ultimos;
                    case Operacion.O:
                        ultimos=agregarSinRepetir(estado1.verificarUltimos(),estado2.verificarUltimos());
                        return ultimos;
                    default:
                        throw new AssertionError();
                }
            } else if (operacion != null) {
                ultimos=estado1.verificarUltimos();
                return ultimos;
            } else {
                ultimos  = estado1.verificarUltimos();
                return ultimos;
            }
        } else {
            return ultimos;
        }
    }

    public ArrayList<Integer> agregarSinRepetir(ArrayList<Integer> c1,ArrayList<Integer> c2){
        for (int i = 0; i < c2.size(); i++) {
            if (!c1.contains(c2.get(i))) {
                c1.add(c2.get(i));
            }
        }
        Collections.sort(c1);
        return c1;
    }
    
    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    @Override
    public String toString() {
        String str = "<";

        if (numero != null) {
            str += "NUM:" + numero + " ";
        }
        if (numeros) {
            str += "NUMEROS";
        } else if (letras) {
            str += "LETRAS";
        } else if (cadena != null) {
            str += cadena;
        } else if (terminal) {
            str += "TERMINAL";

        } else {
            str += estado1.toString();

        }
        str += " ANU:" + anulable + " ";
        if (anulable == null) {
            System.out.println("[[[[");
            if (letras) {
                System.out.println("Letras");
            }
            if (numeros) {
                System.out.println("nu");
            }
            if (terminal) {
                System.out.println("TERMINAL");
            }
            if (cadena != null) {
                System.out.println(cadena);
            }
            if (operacion != null) {
                System.out.println(operacion.getTipo());
            }
            System.out.println("]]]]]");
        }
        if (operacion != null) {
            str += " " + operacion.getTipo();
            if (estado2 != null) {
                str += " " + estado2.toString();
            }
        }
        str += ">";
        return str;
    }

    public EstadoAVL(EstadoAVL estado1, Operacion operacion) {
        this.estado1 = estado1;
        this.operacion = operacion;
    }

    public EstadoAVL(EstadoAVL estado1, EstadoAVL estado2, Operacion operacion) {
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.operacion = operacion;
    }

}
