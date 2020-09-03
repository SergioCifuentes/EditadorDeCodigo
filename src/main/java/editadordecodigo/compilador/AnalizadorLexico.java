/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.compilador;

import editadordecodigo.lenguaje.afd.ConeccionesAFD;
import editadordecodigo.lenguaje.afd.EstadoAFD;
import editadordecodigo.lenguaje.afd.Token;
import editadordecodigo.ui.backend.TextoDeAcciones;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AnalizadorLexico {

    private EstadoAFD inicial;
    private Compilador compi;
    private ArrayList<RutasDeEstados> rutas;
    private int linea = 1;
    private String cadenaFormandose;
    
    

    public AnalizadorLexico(EstadoAFD inicial, Compilador compi) {
        this.inicial = inicial;
        rutas = new ArrayList<>();
        this.compi = compi;

    }

    public String getEntradaCompleta() {
        return compi.obtenerEntradaCompleta();
    }
    

    public Token analizarSiguiente() {
        
        cadenaFormandose = "";
        rutas = new ArrayList<>();
        while (true) {
            String siguiente = compi.obtenerSiguienteCaracter();
            linea+=compi.getSumaLinea();
            System.out.println("SIGUIENTE " + siguiente);
            boolean posible;
            if (siguiente != null) {
                cadenaFormandose += siguiente;
                if (siguiente.equals("\n")) {
                    linea++;
                }
                 posible = generarPaso(siguiente);
            }else{
                if (rutas.isEmpty()) {
                    return null;
                }
                posible=false;
            }

            System.out.println("POSIBLE " + posible);
            System.out.println("RUTAS " + rutas);
            if (!posible) {

                if (rutas.isEmpty()) {
                    TextoDeAcciones.appendToPane(compi.getPrincipal().getjTxtErrores(), "ERROR LEXICO Simbolo No Reconocido\n Linea: " + linea + "\nColumna: " + (compi.getColumnaActual()-1) + "\nInput: " + cadenaFormandose, Color.red, false);
                } else {
                    if (siguiente != null) {
                        cadenaFormandose = cadenaFormandose.substring(0, cadenaFormandose.length() - 1);
                        compi.regresarindiceFinal();
                    } 

                    System.out.println("CADENA FORMA " + cadenaFormandose);
                    
                    String mensajeErrorSiNoTerminal = obtenerMensajeErrorSiNoTerminal(cadenaFormandose);

                    while (!rutas.isEmpty()) {
                        for (int i = 0; i < rutas.size(); i++) {
                            if (rutas.get(i).getConecciones().size() == cadenaFormandose.length()) {
                                ConeccionesAFD ultima = rutas.get(i).getConecciones().get(rutas.get(i).getConecciones().size() - 1);
                                if (ultima.getEstadoFinal().isTerminal()) {
                                    return ultima.getSim();
                                }
                            }
                        }
                        if (cadenaFormandose.length() > 1) {
                            reducirRutas();
                        } else {
                            TextoDeAcciones.appendToPane(compi.getPrincipal().getjTxtErrores(), "ERROR LEXICO Simbolo\n Linea: " + linea + "\nColumna: " + (compi.getColumnaActual()-1) + "\nInput: " + cadenaFormandose, Color.red, false);
                            return null;
                        }

                    }
                    if (rutas.isEmpty()) {
                        TextoDeAcciones.appendToPane(compi.getPrincipal().getjTxtErrores(), mensajeErrorSiNoTerminal + "\n Linea: " + linea + "\nColumna: " + (compi.getColumnaActual()-1) + "\nInput: " + cadenaFormandose, Color.red, false);
                    }

                }
                break;
            }

        }
        return null;
    }
    

    public String obtenerMensajeErrorSiNoTerminal(String cadena) {
        String strInput = "";
        strInput += cadena;
        for (int i = 0; i < rutas.size(); i++) {
            System.out.println("RUTA ERROR " + rutas.get(i).getConecciones().get(rutas.get(i).getConecciones().size() - 1));
            String str = rutas.get(i).getConecciones().get(rutas.get(i).getConecciones().size() - 1).getTokenEsperado();
            if (str != null) {
                return "ERROR LEXICO para " + strInput + " se esperaba " + str;
            }
        }
        return "ERROR LEXICO para " + strInput + " se esperaba " + rutas.get(0).getConecciones().get(rutas.size() - 1).getPrimerTokenEsperado();
    }

    public void reducirRutas() {

        cadenaFormandose = cadenaFormandose.substring(0, cadenaFormandose.length() - 1);
        compi.regresarindiceFinal();
        for (int i = 0; i < rutas.size(); i++) {
            if (rutas.get(i).getConecciones().size() == cadenaFormandose.length()) {
                rutas.get(i).regresarPaso();
                if (rutas.get(i).getConecciones().isEmpty()) {
                    rutas.remove(rutas);
                    i--;
                }
            }
        }

    }

    public boolean generarPaso(String caracter) {
        if (caracter == null) {
            return false;
        }
        System.out.println("Verificando " + caracter);
        if (rutas.isEmpty()) {
            for (int i = 0; i < inicial.getConeccionesAFDs().size(); i++) {
                if (inicial.getConeccionesAFDs().get(i).verificarMovimiento(caracter)) {
                    System.out.println("Siguiente En " + inicial.getConeccionesAFDs().get(i));
                    RutasDeEstados rutaNueva = new RutasDeEstados();
                    rutaNueva.getConecciones().add(inicial.getConeccionesAFDs().get(i));
                    rutas.add(rutaNueva);
                }
            }
            if (rutas.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            for (int i = 0; i < rutas.size(); i++) {
                ArrayList<ConeccionesAFD> rutasSiguientes = rutas.get(i).verificarSiguiente(caracter);
                if (!rutasSiguientes.isEmpty()) {
                    ArrayList<ConeccionesAFD> antiguas = new ArrayList<>();
                    antiguas.addAll(rutas.get(i).getConecciones());
                    rutas.remove(i);
                    for (int j = 0; j < rutasSiguientes.size(); j++) {
                        RutasDeEstados rutasEstados = new RutasDeEstados(antiguas, rutasSiguientes.get(j));
                        rutas.add(i + j, rutasEstados);
                    }

                }
            }
            for (int i = 0; i < rutas.size(); i++) {
                if (rutas.get(i).getConecciones().size() == cadenaFormandose.length()) {
                    return true;
                }
            }

        }
        return false;
    }

}
