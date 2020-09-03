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

    public EstadoAVL getEstado2() {
        return estado2;
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean isNumeros() {
        return numeros;
    }

    public boolean isLetras() {
        return letras;
    }

    public String getCadena() {
        return cadena;
    }

    public boolean isTerminal() {
        return terminal;
    }
    
    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }
    
    public ArrayList<Integer> getUltimos() {
        return ultimos;
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
                        
                        anulable = estado1.verificarAnulable();
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

    public EstadoAVL getEstado1() {
        return estado1;
    }
    
    public void obternerSiguientes(TablaDeSiguientes tabla) {
        if (operacion != null) {
            System.out.println("OPPPPPPPPPPPP"+operacion.getTipo()+estado1.primeros);
            if (operacion.getTipo() == Operacion.CONCATENACION) {
                System.out.println("CON "+estado1.ultimos+"  <- "+estado2.primeros);
                for (int i = 0; i < estado1.ultimos.size(); i++) {
                    for (int j = 0; j < estado2.primeros.size(); j++) {
                        System.out.println("ES"+(estado1.ultimos.get(i)-1));
                        if (!tabla.getTable().get(estado1.ultimos.get(i)-1).contains(estado2.primeros.get(j))) {
                            tabla.getTable().get(estado1.ultimos.get(i)-1).add(estado2.primeros.get(j));
                            Collections.sort(tabla.getTable().get(estado1.ultimos.get(i)-1));
                        }
                    }
                    
                }
            } else if (operacion.getTipo() == Operacion.CERO_O_MAS_VECES||operacion.getTipo() == Operacion.UNA_O_MAS_VECES) {
                System.out.println("OOOP "+estado1.ultimos+"  <- "+estado1.primeros);
                for (int i = 0; i < estado1.ultimos.size(); i++) {
                    for (int j = 0; j < estado1.primeros.size(); j++) {
                        
                        if (!tabla.getTable().get(estado1.ultimos.get(i)-1).contains(estado1.primeros.get(j))) {
                            tabla.getTable().get(estado1.ultimos.get(i)-1).add(estado1.primeros.get(j));
                            Collections.sort(tabla.getTable().get(estado1.ultimos.get(i)-1));
                        }
                    }
                }
            } 
           
            estado1.obternerSiguientes(tabla);
            if (estado2 != null) {
                estado2.obternerSiguientes(tabla);
            }
        }
    }
    
    public ArrayList<Integer> verificarPrimeros() {
        if (primeros.isEmpty()) {
            if (estado2 != null) {
                
                switch (operacion.tipo) {
                    case Operacion.CONCATENACION:
                        if (estado1.anulable) {
                            primeros = agregarSinRepetir(estado1.verificarPrimeros(), estado2.verificarPrimeros());
                        } else {
                            primeros = estado1.verificarPrimeros();
                            estado2.verificarPrimeros();
                        }
                        return primeros;
                    case Operacion.O:
                        primeros = agregarSinRepetir(estado1.verificarPrimeros(), estado2.verificarPrimeros());
                        return primeros;
                    default:
                        throw new AssertionError();
                }
            } else if (operacion != null) {
                primeros = estado1.verificarPrimeros();
                return primeros;
            } else {
                primeros = estado1.verificarPrimeros();
                return primeros;
            }
        } else {
            return primeros;
        }
    }

    public Operacion getOperacion() {
        return operacion;
    }
    
    public ArrayList<Integer> verificarUltimos() {
        if (ultimos.isEmpty()) {
            System.out.println("SOSOSOSOSOS"+estado2);
            if (operacion!=null) {
                System.out.println(operacion.tipo);
            }
            System.out.println("=======");
            if (estado2 != null) {
                
                switch (operacion.tipo) {
                    case Operacion.CONCATENACION:
                        if (estado2.anulable) {
                            ultimos = agregarSinRepetir(estado1.verificarUltimos(), estado2.verificarUltimos());
                        } else {
                            ultimos = estado2.verificarPrimeros();
                            estado1.verificarUltimos();
                        }
                        return ultimos;
                    case Operacion.O:
                        ultimos = agregarSinRepetir(estado1.verificarUltimos(), estado2.verificarUltimos());
                        System.out.println(estado1.ultimos+"   "+estado2.ultimos);
                        System.out.println("UUUUUUUUUUUUUUUUUUUUUU "+ultimos);
                        return ultimos;
                    default:
                        throw new AssertionError();
                }
            } else if (operacion != null) {
                
                ultimos = estado1.verificarUltimos();
                System.out.println("QQQQQQQQ "+operacion.tipo+" "+estado1.primeros+"  "+estado1.ultimos);
                return ultimos;
            } else {
                ultimos = estado1.verificarUltimos();
                return ultimos;
            }
        } else {
            return ultimos;
        }
    }
    
    public ArrayList<Integer> agregarSinRepetir(ArrayList<Integer> c1, ArrayList<Integer> c2) {
        ArrayList<Integer> aux = new ArrayList<>();
        aux.addAll(c1);
        for (int i = 0; i < c2.size(); i++) {
            if (!aux.contains(c2.get(i))) {
                aux.add(c2.get(i));
            }
        }
        Collections.sort(aux);
        return aux;
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
