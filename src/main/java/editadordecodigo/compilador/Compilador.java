/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador;

import editadordecodigo.compilador.analisadorSintactico.AnalizadorSintactico;
import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.ui.Principal;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Compilador {

    private Lenguaje lenguaje;
    private String code;
    private Principal principal;
    private int columnaActual = 0;
    private int indiceFinal;
    private int sumaLinea = 0;
    private AnalizadorSintactico as;

    public Compilador(Lenguaje lenguaje, String code, Principal principal) {
        this.lenguaje = lenguaje;
        this.code = code;
        this.principal = principal;
    }

    public void compilar() {
        indiceFinal = 0;
        AnalizadorLexico al = new AnalizadorLexico(lenguaje.getEstadoInical(), this);
        as = new AnalizadorSintactico(al, lenguaje, principal);
        as.start();

    }

    public ArrayList<String[]> obtenerPila() {
        return as.obtenerPila();
    }

    public void regresarindiceFinal() {
        indiceFinal--;
    }

    public String obtenerSiguienteCaracter() {
        sumaLinea = 0;

        System.out.println("Code " + code);
        System.out.println("Indeice " + indiceFinal);
        System.out.println("Len " + code.length());

        if (code.length() <= indiceFinal) {
            return null;
        }
        //Verificacion de comentarios
        if (String.valueOf(code.charAt(indiceFinal)).equals("/")) {
            if (indiceFinal + 1 <= code.length() - 1 && String.valueOf(code.charAt(indiceFinal + 1)) != null && String.valueOf(code.charAt(indiceFinal + 1)).equals("/")) {
                indiceFinal++;
                columnaActual++;
                while (true) {
                    indiceFinal++;
                    if (code.length() <= indiceFinal) {
                        return null;
                    }
                    String siguiente = String.valueOf(code.charAt(indiceFinal));
                    System.out.println("SIGGGGGG " + siguiente + " " + indiceFinal);
                    if (siguiente == null || siguiente.equals("\n")) {
                        if (siguiente != null && siguiente.equals("\n")) {
                            System.out.println("Comentario " + indiceFinal + " " + sumaLinea);
                            sumaLinea++;
                            resetColumanActual();
                        }
                        indiceFinal++;
                        columnaActual++;
                        break;
                    }
                }
            } else if (indiceFinal + 1 <= code.length() - 1 && String.valueOf(code.charAt(indiceFinal + 1)) != null && String.valueOf(code.charAt(indiceFinal + 1)).equals("*")) {
                indiceFinal++;
                columnaActual++;
                while (true) {
                    indiceFinal++;
                    columnaActual++;
                    if (code.length() <= indiceFinal) {
                        return null;
                    }
                    String siguiente = String.valueOf(code.charAt(indiceFinal));
                    System.out.println("SIGGGGGG " + siguiente + " " + indiceFinal);
                    if (siguiente == null || siguiente.equals("\n")) {
                        if (siguiente != null && siguiente.equals("\n")) {
                            System.out.println("SUMAR LINEA");
                            sumaLinea++;
                            resetColumanActual();
                        }

                    } else if (siguiente.equals("*") && indiceFinal + 1 <= code.length() - 1 && String.valueOf(code.charAt(indiceFinal + 1)).equals("/")) {
                        indiceFinal++;
                        indiceFinal++;
                        columnaActual++;
                        columnaActual++;
                        break;
                    }
                    {

                    }
                }
            } else {
                return String.valueOf(code.charAt(indiceFinal));

            }
        }
        if (code.length() <= indiceFinal) {
            return null;
        }
        System.out.println(String.valueOf(code.charAt(indiceFinal)));
        String aRegresar = String.valueOf(code.charAt(indiceFinal));
        indiceFinal++;
        columnaActual++;
        return aRegresar;

    }

    public int getSumaLinea() {
        return sumaLinea;
    }

    public void resetColumanActual() {
        columnaActual = 0;
    }

    public int getColumnaActual() {
        return columnaActual;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String obtenerEntradaCompleta() {
        if (code.length() <= indiceFinal) {
            return "";
        } else {
            return code.substring(indiceFinal);
        }
    }
}
