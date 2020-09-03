/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.archivos;

import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.ui.Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class ManejadorDeArchivos {

    private ArrayList<Archivo> archivos;

    public ManejadorDeArchivos() {
        archivos = new ArrayList<>();
    }

    public void addArchivo(Archivo archivo) {
        archivos.add(archivo);
    }

    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void abrirArchivo(File file, ArrayList<Lenguaje> lenguajes, Principal pr) {
        Lenguaje ex = null;
        for (int i = 0; i < lenguajes.size(); i++) {
            System.out.println(file.getName() + "  " + lenguajes.get(i).getExtension());
            if (file.getName().endsWith("." + lenguajes.get(i).getExtension())) {
                ex = lenguajes.get(i);
            }
        }
        if (ex != null) {
            Archivo nuevo = new Archivo(file.getName(), ex, file);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            String texto = "";
            String st;
            try {
                while ((st = br.readLine()) != null) {
                    texto += st + "\n";
                }
                if (texto.endsWith("\n")) {
                    System.out.println("salto");
                    texto = texto.substring(0, texto.length() - 1);
                }
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            nuevo.setText(texto);
            pr.agregarArchivo(nuevo);

        } else {

            JOptionPane.showMessageDialog(pr, "Archivo no cuenta con una extension de lenguaje reconocido", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void guardarArchivo(int numeroArchivo, Principal pr) {
        Archivo arc = archivos.get(numeroArchivo);
        if (arc.getUbicacion() != null) {
            System.out.println("gg");
            File file = arc.getUbicacion();
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException ex) {
                Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(arc.getText());
            try {
                fw.write(arc.getText());
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(pr, "Se guardo correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Seleccione Donde Desea GuardarElArchivo");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showOpenDialog(pr);
            File file = jfc.getSelectedFile();
            if (file != null) {
                arc.setUbicacion(new File(file.getPath() + "/" + arc.getNombre() + "." + arc.getLenguaje().getExtension()));
                guardarArchivo(numeroArchivo, pr);
            }
        }
    }

    public void guardarArchivoComo(int numeroArchivo,Principal pr) {
        Archivo arc = archivos.get(numeroArchivo);
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione Donde Desea GuardarElArchivo");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showOpenDialog(pr);
        File file = jfc.getSelectedFile();
        if (file != null) {
            arc.setUbicacion(new File(file.getPath() + "/" + arc.getNombre() + "." + arc.getLenguaje().getExtension()));
            guardarArchivo(numeroArchivo, pr);
        }
    }

}
