/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.ui.backend;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author sergio
 */
public class Manuales {
    private static final String USARIO_PATH="./Documentacion/Manual De Usuario (Compilador De Lenguajes).pdf";
    private static final String TECNICO_PATH="./Documentacion/Manual Técnico (Compilador De Lenguajes).pdf";
    private static final String INSTALACION_DESINSTALACION="./Documentacion/Manual De Instalación y Desinstalación (Compilador De Lenguajes).pdf";
    public void abrirManualDeUsuario(){
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(USARIO_PATH);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    }
    
    public void abrirManualDeTecnico(){
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(TECNICO_PATH);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    }
    public void abrirManualDeInstalacion(){
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(INSTALACION_DESINSTALACION);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    }
}
