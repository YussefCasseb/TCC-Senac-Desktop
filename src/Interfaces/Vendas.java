package Interfaces;

import DAO.VendasDAO;
import Model.VendaTableModel;
import PDF.VendasPDF;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

public class Vendas extends javax.swing.JInternalFrame {
    
    Logger logger = Logger.getLogger("file");
    VendaTableModel modelo = new VendaTableModel();
    VendasDAO rdao = new VendasDAO();

    public Vendas() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Erro: " +e);
	}
        
        initComponents();
        
        modelo.RecarregaTabela();
        jTRelatorio.setModel(modelo);
        
        jLData.setVisible(false);
        jLData1.setVisible(false);
        jData1.setVisible(false);    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jData = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTRelatorio = new javax.swing.JTable();
        jBBusca = new javax.swing.JButton();
        jBTudo = new javax.swing.JButton();
        jLData = new javax.swing.JLabel();
        jBPdf = new javax.swing.JButton();
        jPGrafico = new javax.swing.JPanel();
        jCTipo = new javax.swing.JComboBox<>();
        jData1 = new com.toedter.calendar.JDateChooser();
        jLData1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Vendas - Tabela");
        setPreferredSize(new java.awt.Dimension(500, 500));

        jData.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jTRelatorio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTRelatorio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTRelatorio);

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

        jBTudo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search-icon.png"))); // NOI18N
        jBTudo.setText("Listar Tudo");
        jBTudo.setContentAreaFilled(false);
        jBTudo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBTudo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTudoActionPerformed(evt);
            }
        });

        jLData.setForeground(new java.awt.Color(255, 0, 0));
        jLData.setText("Data Obrigatória");

        jBPdf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pdf-icon.png"))); // NOI18N
        jBPdf.setText("Gerar PDF");
        jBPdf.setContentAreaFilled(false);
        jBPdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBPdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPGraficoLayout = new javax.swing.GroupLayout(jPGrafico);
        jPGrafico.setLayout(jPGraficoLayout);
        jPGraficoLayout.setHorizontalGroup(
            jPGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPGraficoLayout.setVerticalGroup(
            jPGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jCTipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Intervalo" }));
        jCTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTipoActionPerformed(evt);
            }
        });

        jData1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLData1.setForeground(new java.awt.Color(255, 0, 0));
        jLData1.setText("Data Obrigatória");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBTudo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBPdf))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jData, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jData1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLData1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLData1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jData1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBBusca)
                        .addComponent(jBPdf))
                    .addComponent(jBTudo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscaActionPerformed
        String dt1, dt2;
        if(jCTipo.getSelectedItem().equals("Dia")){
            if(jData.getDate() != null){
                dt1 = new SimpleDateFormat("yyyy/MM/dd").format(jData.getDate());
                modelo.Search(dt1,dt1);
                jLData.setVisible(false);
                jLData1.setVisible(false);
                jData.setDate(null);
                jData1.setDate(null);
            }else{
                jLData.setVisible(true);
            }
        }else{
            if(jData.getDate() != null && jData1.getDate() != null){
                dt1 = new SimpleDateFormat("yyyy/MM/dd").format(jData.getDate());
                dt2 = new SimpleDateFormat("yyyy/MM/dd").format(jData1.getDate());
                modelo.Search(dt1,dt2);
                jLData.setVisible(false);
                jLData1.setVisible(false);
                jData.setDate(null);
                jData1.setDate(null);
            }else{
                jLData.setVisible(true);
                jLData1.setVisible(true);
            }
        }
    }//GEN-LAST:event_jBBuscaActionPerformed

    private void jBTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTudoActionPerformed
        modelo.RecarregaTabela();
        jCTipo.setSelectedItem("Dia");
        jData.setDate(null);
        jData1.setDate(null);
    }//GEN-LAST:event_jBTudoActionPerformed

    private void jBPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPdfActionPerformed
        VendasPDF pdf = new VendasPDF();
        String userhome = System.getProperty("user.home");
        JFileChooser Arquivo = new JFileChooser(userhome + "\\Desktop");
        Arquivo.setFileFilter(new FileNameExtensionFilter("Arquivos PDF", "pdf"));
        Arquivo.setAcceptAllFileFilterUsed(false);
        int rs = Arquivo.showSaveDialog(this);
        if(jCTipo.getSelectedItem().equals("Dia") && jTRelatorio.getSelectedRow() != -1){
            if(rs != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(this, "Erro ao Salvar!","Atenção",JOptionPane.ERROR_MESSAGE);
            }else{
                    String dt = String.valueOf(modelo.PegaDadosLinha(jTRelatorio.getSelectedRow()).getData());
                    String[] dtstr = dt.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt);
            }
        }else{
            if(rs != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(this, "Erro ao Salvar!","Atenção",JOptionPane.ERROR_MESSAGE);
            }else{
                    int row = jTRelatorio.getRowCount();
                    String dt = String.valueOf(jTRelatorio.getValueAt(0, 0));
                    String dt2 = String.valueOf(jTRelatorio.getValueAt(row-1, 0));
                    String[] dtstr = dt.split("/");
                    String[] dtstr2 = dt2.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    LocalDate ndt2 = LocalDate.of(Integer.parseInt(dtstr2[2]), Integer.parseInt(dtstr2[1]), Integer.parseInt(dtstr2[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt2);
            }
        }
    }//GEN-LAST:event_jBPdfActionPerformed

    private void jCTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTipoActionPerformed
        if(jCTipo.getSelectedItem().equals("Intervalo")){
            jData1.setVisible(true);
            jLData.setVisible(false);
            jLData1.setVisible(false);
            jData.setDate(null);
        }else{
            jData1.setVisible(false);
            jLData.setVisible(false);
            jLData1.setVisible(false);
        }
    }//GEN-LAST:event_jCTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBusca;
    private javax.swing.JButton jBPdf;
    private javax.swing.JButton jBTudo;
    private javax.swing.JComboBox<String> jCTipo;
    private com.toedter.calendar.JDateChooser jData;
    private com.toedter.calendar.JDateChooser jData1;
    private javax.swing.JLabel jLData;
    private javax.swing.JLabel jLData1;
    private javax.swing.JPanel jPGrafico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTRelatorio;
    // End of variables declaration//GEN-END:variables
}
