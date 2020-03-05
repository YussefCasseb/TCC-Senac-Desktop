package Interfaces;

import DAO.MesaDAO;
import java.awt.event.KeyEvent;

public class FecharPedido extends javax.swing.JInternalFrame {
    
    private double vtotal;
    private int div;

    public FecharPedido() {
        initComponents();

        jBTotal.setText("R$ " + String.valueOf(ModificarMesas.valor));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTDiv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTSepara = new javax.swing.JTextField();
        jBFechar = new javax.swing.JButton();
        jBTotal = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setText("Dividir Valor");

        jTDiv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTDivKeyPressed(evt);
            }
        });

        jLabel2.setText("Pagar à parte");

        jBFechar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pagar.png"))); // NOI18N
        jBFechar.setText("Pagar");
        jBFechar.setBorderPainted(false);
        jBFechar.setContentAreaFilled(false);
        jBFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBFechar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFecharActionPerformed(evt);
            }
        });

        jBTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBTotal.setForeground(new java.awt.Color(4, 147, 0));
        jBTotal.setText("R$ 0,00");
        jBTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBTotal.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTDiv)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTSepara)))
                    .addComponent(jBTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTSepara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBFechar)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTDivKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDivKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jTDiv.getText().isEmpty()){
                jBTotal.setText("R$ " + String.valueOf(ModificarMesas.valor));
            }else{
                vtotal = ModificarMesas.valor / Integer.parseInt(jTDiv.getText());
                jBTotal.setText("R$ " + String.valueOf(vtotal));
            }
        }
    }//GEN-LAST:event_jTDivKeyPressed

    private void jBFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFecharActionPerformed
        MesaDAO mdao = new MesaDAO();
        if(jTSepara.getText().isEmpty()){
            mdao.Fechar(ModificarMesas.comanda);
        }else{
            mdao.Pago(ModificarMesas.comanda, Double.parseDouble(jTSepara.getText().replace(",", ".")));
            vtotal = ModificarMesas.valor - Double.parseDouble(jTSepara.getText().replace(",", "."));
            jBTotal.setText("R$ " + String.valueOf(vtotal));
        }
    }//GEN-LAST:event_jBFecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBFechar;
    private javax.swing.JButton jBTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTDiv;
    private javax.swing.JTextField jTSepara;
    // End of variables declaration//GEN-END:variables
}
