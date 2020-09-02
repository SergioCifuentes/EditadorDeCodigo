/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.ui;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sergio
 */
public class Pila extends javax.swing.JFrame {

    /**
     * Creates new form Pila
     */
    public Pila(ArrayList<String[]> pilaAMostrar) {
        initComponents();
        agregarColumnas(pilaAMostrar);
        
    }
public void agregarColumnas(ArrayList<String[]> pilaAMostrar){
    DefaultTableModel model= (DefaultTableModel) tablaPila.getModel();
    for (int i = 0; i < pilaAMostrar.size(); i++) {
        model.addRow(new Object[]{i+1,pilaAMostrar.get(i)[0],pilaAMostrar.get(i)[1],pilaAMostrar.get(i)[2],pilaAMostrar.get(i)[3]});
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tablaPila = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaPila.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Pila", "Simbolos", "Entrada", "Accion"
            }
        ));
        scroll.setViewportView(tablaPila);
        if (tablaPila.getColumnModel().getColumnCount() > 0) {
            tablaPila.getColumnModel().getColumn(0).setMinWidth(15);
            tablaPila.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaPila.getColumnModel().getColumn(0).setMaxWidth(20);
            tablaPila.getColumnModel().getColumn(1).setMinWidth(90);
            tablaPila.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaPila.getColumnModel().getColumn(1).setMaxWidth(100);
            tablaPila.getColumnModel().getColumn(2).setMinWidth(90);
            tablaPila.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaPila.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaPila;
    // End of variables declaration//GEN-END:variables
}