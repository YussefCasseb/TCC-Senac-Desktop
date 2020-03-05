package Interfaces;

import DAO.AdicionaisDAO;
import Model.AdicionaisTableModel;
import Objetos.OAdicionais;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Adicionais extends javax.swing.JInternalFrame {
    
    AdicionaisTableModel modelo = new AdicionaisTableModel();
    AdicionaisDAO adao = new AdicionaisDAO();

    public Adicionais() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Erro: " +e);
	}
        
        initComponents();
        
        attBox();
        
        jTNome.requestFocus();
        
        modelo.RecarregaTabela();
        jTProdutos.setModel(modelo);
        
        jLNome.setVisible(false);
        jLValor.setVisible(false);
        jLNomeB.setVisible(false);
        jLProd.setVisible(false);
        
    }
    
    public final void attBox(){
        for(OAdicionais a : adao.pegaProd()){
            jCProd.addItem(a.getCProd());
        }
    }
    
    public void LimpaCampos(){
        jTNome.setText("");
        jTValor.setText("");
        jTBusca.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTNome = new javax.swing.JTextField();
        jTValor = new javax.swing.JTextField();
        jBCad = new javax.swing.JButton();
        jBAlt = new javax.swing.JButton();
        jBExc = new javax.swing.JButton();
        jTBusca = new javax.swing.JTextField();
        jBBusca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTProdutos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jBListar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLNome = new javax.swing.JLabel();
        jLValor = new javax.swing.JLabel();
        jLNomeB = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCProd = new javax.swing.JComboBox<>();
        jLProd = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Adicionais");
        setPreferredSize(new java.awt.Dimension(500, 500));

        jTNome.setToolTipText("");

        jBCad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBCad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add-icon.png"))); // NOI18N
        jBCad.setText("Cadastrar");
        jBCad.setContentAreaFilled(false);
        jBCad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBCad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadActionPerformed(evt);
            }
        });

        jBAlt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBAlt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/edit-icon.png"))); // NOI18N
        jBAlt.setText("Alterar");
        jBAlt.setContentAreaFilled(false);
        jBAlt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAlt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAltActionPerformed(evt);
            }
        });

        jBExc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBExc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete-icon.png"))); // NOI18N
        jBExc.setText("Excluir");
        jBExc.setContentAreaFilled(false);
        jBExc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBExc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcActionPerformed(evt);
            }
        });

        jBBusca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search-icon.png"))); // NOI18N
        jBBusca.setText("Buscar");
        jBBusca.setContentAreaFilled(false);
        jBBusca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBBusca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscaActionPerformed(evt);
            }
        });

        jTProdutos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTProdutos);

        jBListar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search-icon.png"))); // NOI18N
        jBListar.setText("Listar Tudo");
        jBListar.setContentAreaFilled(false);
        jBListar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBListar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBListarActionPerformed(evt);
            }
        });

        jLabel1.setText("Adicional:");

        jLabel2.setText("Valor:");

        jLabel3.setText("Buscar Produto:");

        jLNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLNome.setForeground(new java.awt.Color(255, 0, 0));
        jLNome.setText("Nome Obrigatório");

        jLValor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLValor.setForeground(new java.awt.Color(255, 0, 0));
        jLValor.setText("Valor Obrigatório");

        jLNomeB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLNomeB.setForeground(new java.awt.Color(255, 0, 0));
        jLNomeB.setText("Nome Obrigatório");

        jLabel4.setText("Produto:");

        jLProd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLProd.setForeground(new java.awt.Color(255, 0, 0));
        jLProd.setText("Produto Obrigatório");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBListar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLNomeB))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLNome))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLProd))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLValor)))))
                        .addContainerGap(136, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBCad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAlt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBExc)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLValor))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCad)
                    .addComponent(jBAlt)
                    .addComponent(jBExc))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLNomeB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBBusca)
                    .addComponent(jBListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadActionPerformed
        OAdicionais a = new OAdicionais();
        
        if(jTNome.getText().isEmpty()){
            jLNome.setVisible(true);
            jLValor.setVisible(false);
            jTNome.requestFocus();
        }else if(jTValor.getText().isEmpty()){
            jLValor.setVisible(true);
            jLNome.setVisible(false);
            jTValor.requestFocus();
        }else{
            try{
                a.setAdicional(jTNome.getText());
                a.setProd(jCProd.getSelectedItem().toString());
                a.setValor(Double.parseDouble(jTValor.getText().replace(",", ".")));
                adao.Create(a);
                adao.UpdateProdAdic(jCProd.getSelectedItem().toString());
                modelo.RecarregaTabela();
                LimpaCampos();
                jLValor.setVisible(false);
                jLNome.setVisible(false);
                if(AdicionaisDAO.alert.equals("Falha ao Inserir!")){
                    JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Atenção",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(NumberFormatException e){
                jLValor.setVisible(true);
                jLNome.setVisible(false);
            }
        }
    }//GEN-LAST:event_jBCadActionPerformed

    private void jBAltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAltActionPerformed
        if(jTProdutos.getSelectedRow() != -1){
            if(jTNome.getText().isEmpty()){
                jLNome.setVisible(true);
                jLValor.setVisible(false);
                jTNome.requestFocus();
            }else if(jTValor.getText().isEmpty()){
                jLValor.setVisible(true);
                jLNome.setVisible(false);
                jTValor.requestFocus();
            }else{
                try{
                    modelo.setValueAt(jTNome.getText(), jTProdutos.getSelectedRow(), 0);
                    modelo.setValueAt(jCProd.getSelectedItem().toString(), jTProdutos.getSelectedRow(), 1);
                    modelo.setValueAt(jTValor.getText().replace(",", "."), jTProdutos.getSelectedRow(), 2);
                    OAdicionais a = modelo.PegaDadosLinha(jTProdutos.getSelectedRow());
                    adao.Update(a);
                    modelo.RecarregaTabela();
                    LimpaCampos();
                    jLValor.setVisible(false);
                    jLNome.setVisible(false);
                    if(AdicionaisDAO.alert.equals("Falha ao Atualizar!")){
                        JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Atenção",JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(NumberFormatException e){
                    jLValor.setVisible(true);
                    jLNome.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_jBAltActionPerformed

    private void jBExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcActionPerformed
        if(jTProdutos.getSelectedRow() != -1){
            adao.Delete(modelo.PegaDadosLinha(jTProdutos.getSelectedRow()).getCod_adic());
            adao.DeleteProdAdic(modelo.PegaDadosLinha(jTProdutos.getSelectedRow()).getProd());
            modelo.RecarregaTabela();
            LimpaCampos();
            if(AdicionaisDAO.alert.equals("Falha ao Excluir!")){
                JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Atenção",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, AdicionaisDAO.alert,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBExcActionPerformed

    private void jTProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProdutosMouseClicked
        jTNome.setText(String.valueOf(modelo.getValueAt(jTProdutos.getSelectedRow(), 0)));
        jCProd.setSelectedItem(String.valueOf(modelo.getValueAt(jTProdutos.getSelectedRow(), 1)));
        jTValor.setText(String.valueOf(modelo.getValueAt(jTProdutos.getSelectedRow(), 2)));
    }//GEN-LAST:event_jTProdutosMouseClicked

    private void jBBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscaActionPerformed
        if(jTBusca.getText().isEmpty()){
            jLNomeB.setVisible(true);
        }else{
            modelo.Search(jTBusca.getText());
            LimpaCampos();
            jLNomeB.setVisible(false);
        }
    }//GEN-LAST:event_jBBuscaActionPerformed

    private void jBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListarActionPerformed
        modelo.RecarregaTabela();
        LimpaCampos();
    }//GEN-LAST:event_jBListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlt;
    private javax.swing.JButton jBBusca;
    private javax.swing.JButton jBCad;
    private javax.swing.JButton jBExc;
    private javax.swing.JButton jBListar;
    private javax.swing.JComboBox<String> jCProd;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLNomeB;
    private javax.swing.JLabel jLProd;
    private javax.swing.JLabel jLValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTBusca;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTable jTProdutos;
    private javax.swing.JTextField jTValor;
    // End of variables declaration//GEN-END:variables
}
