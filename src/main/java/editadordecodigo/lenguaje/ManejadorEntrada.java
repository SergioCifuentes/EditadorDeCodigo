/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.lenguaje;

import editadordecodigo.cup.AnalizadorSintacticoLenguaje;
import editadordecodigo.cup.semantico.TablaDeSimbolos;
import editadordecodigo.jflex.AnalizadorLexicoLenguaje;
import editadordecodigo.ui.Principal;
import editadordecodigo.ui.backend.TextoDeAcciones;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author sergio
 */
public class ManejadorEntrada {
    public static final String EXTENSION=".len";
    public void cargarLenguaje(Principal pc){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione El Archivo "+EXTENSION);

        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(pc);
        File file = jfc.getSelectedFile();
        if (file != null&& file.getPath().contains(EXTENSION)) {
            try {
                String str = obtenerCodigoFuente(file);
                if (str!=null) {
                    AnalizadorLexicoLenguaje all = new  AnalizadorLexicoLenguaje(new StringReader(str));
                    TablaDeSimbolos tds = new TablaDeSimbolos();
                    AnalizadorSintacticoLenguaje asl = new AnalizadorSintacticoLenguaje(all);
                    asl.setTablaDeSimbolos(tds);
                    asl.setFrame(pc);
                    asl.parse();
                }else{
                    TextoDeAcciones.appendToPane(pc.getjTxtErrores(),"Secciones insuficientes del archivo (.len)", Color.red, true);
                }
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManejadorEntrada.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ManejadorEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
                 TextoDeAcciones.appendToPane(pc.getjTxtErrores(),"El archivo debe tener la extension (.len)", Color.red, true);
               
        }
    }
    
    public String obtenerCodigoFuente(File file){
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str = "";
        int aux = 0;
        while (true) {
            String linea=null;
            try {
                linea = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (linea==null) {
                break;
            }else if (linea.contains("%%")) {
                
                aux++;
                if (aux==1||aux==2) {
                    linea=linea.replaceAll("%%", "%%%");
                }
            }
            str+=linea+"\n";
           
  
        }
        if (aux==4) {
            return str;
        }else{
            return null;
        }
        
    }
}
