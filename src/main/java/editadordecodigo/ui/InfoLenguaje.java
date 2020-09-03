/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editadordecodigo.ui;

import editadordecodigo.lenguaje.Lenguaje;
import editadordecodigo.ui.backend.TextoDeAcciones;
import java.awt.Color;

/**
 *
 * @author sergio
 */
public class InfoLenguaje extends javax.swing.JFrame {

    /**
     * Creates new form InfoLenguaje
     */
    public InfoLenguaje(Lenguaje lenguaje) {
        initComponents();
        lblNombre.setText(lenguaje.getNombre());
        if (lenguaje.getAutor()!=null) {
            lblAutor.setText(lenguaje.getAutor());
        }
        if (lenguaje.getExtension()!=null) {
            lblExtension.setText(lenguaje.getExtension());
        }
        if (lenguaje.getLanzamiento()!=null) {
        lblLanzamiento.setText(lenguaje.getLanzamiento());
        }
        if (lenguaje.getVersion()!=null) {
        lblVersion.setText(lenguaje.getVersion());
        }
        
        
        
        this.getContentPane().setBackground( new Color(0,0,102) );
        for (int i = 0; i < lenguaje.getTablaLR().getProduccions().size(); i++) {
            TextoDeAcciones.appendToPane(jTextPane1,lenguaje.getTablaLR().getProduccions().get(i).getNoTerminal().getNombre(), Color.red, false);
            TextoDeAcciones.appendToPane(jTextPane1," -> ", Color.pink, false);
            TextoDeAcciones.appendToPane(jTextPane1,lenguaje.getTablaLR().getProduccions().get(i).getProduccionesString()+"\n", Color.WHITE, false);
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

        lblNombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblExtension = new javax.swing.JLabel();
        lblLanzamiento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 102));

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("jLabel1");

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setText("Autor: ");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(0, 0, 102));
        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText("Lanzamiento:");
        jLabel2.setOpaque(true);

        jTextPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTextPane1);

        jLabel3.setBackground(new java.awt.Color(0, 0, 102));
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("Version:");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("Extension:");
        jLabel4.setOpaque(true);

        lblAutor.setBackground(new java.awt.Color(0, 0, 102));
        lblAutor.setForeground(new java.awt.Color(204, 255, 204));
        lblAutor.setText("----");
        lblAutor.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblAutor.setOpaque(true);

        lblVersion.setBackground(new java.awt.Color(0, 0, 102));
        lblVersion.setForeground(new java.awt.Color(204, 255, 204));
        lblVersion.setText("----");
        lblVersion.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblVersion.setOpaque(true);

        lblExtension.setBackground(new java.awt.Color(0, 0, 102));
        lblExtension.setForeground(new java.awt.Color(204, 255, 204));
        lblExtension.setText("----");
        lblExtension.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblExtension.setOpaque(true);

        lblLanzamiento.setBackground(new java.awt.Color(0, 0, 102));
        lblLanzamiento.setForeground(new java.awt.Color(204, 255, 204));
        lblLanzamiento.setText("----");
        lblLanzamiento.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblLanzamiento.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Producciones:");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(lblExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(67, 67, 67)
                        .addComponent(lblVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(lblLanzamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(78, 78, 78)
                        .addComponent(lblAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(lblNombre)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblAutor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblLanzamiento))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblVersion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblExtension))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblExtension;
    private javax.swing.JLabel lblLanzamiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblVersion;
    // End of variables declaration//GEN-END:variables
}
