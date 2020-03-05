package Interfaces;

import DAO.MesaDAO;
import DAO.RelatorioDAO;
import Model.TabelaTableModel;
import Objetos.Relatorio;
import PDF.GerarPDF;
import java.awt.BorderLayout;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class RTabela extends javax.swing.JInternalFrame {
    
    Logger logger = Logger.getLogger("file");
    TabelaTableModel modelo = new TabelaTableModel();
    RelatorioDAO rdao = new RelatorioDAO();
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart grafico = ChartFactory.createBarChart("Relatório de Vendas","Informações","Quantidade de Vendas/Faturamento",dataset,PlotOrientation.VERTICAL,true,true,false);

    public RTabela(String dt1, String dt2) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Erro: " +e);
	}
        
        initComponents();
        
        geraGrafico(dt1, dt2);
        
        modelo.RecarregaTabela();
        jTRelatorio.setModel(modelo);
        
        jLData.setVisible(false);
        jLData1.setVisible(false);
        jData1.setVisible(false);
        
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                if(!MesaDAO.att.isEmpty()){
                    rdao.read();
                    geraGrafico(new SimpleDateFormat("yyyy/MM/dd").format(RelatorioDAO.dt), new SimpleDateFormat("yyyy/MM/dd").format(RelatorioDAO.dt));
                    modelo.RecarregaTabela();
                    MesaDAO.att = "";
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 10000);
        
    }
    
    public void geraGrafico(String b1, String b2){
        for (Relatorio r: rdao.SearchSoma(b1, b2)) {
            dataset.addValue(r.getFaturamento(), "Valor Total", "");
            dataset.addValue(r.getQtde_vendida(), "Quantidade de Vendas", "");
        }
        
        jPGrafico.setLayout(new BorderLayout());
        
        jPGrafico.add(new ChartPanel(grafico));
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
        jBImg = new javax.swing.JButton();
        jCTipo = new javax.swing.JComboBox<>();
        jData1 = new com.toedter.calendar.JDateChooser();
        jLData1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Relatório - Tabela/Gráfico");
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
        jTRelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTRelatorioMouseClicked(evt);
            }
        });
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPGraficoLayout.setVerticalGroup(
            jPGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jBImg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/jpg-icon.png"))); // NOI18N
        jBImg.setText("Gerar Imagem");
        jBImg.setContentAreaFilled(false);
        jBImg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBImg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImgActionPerformed(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTudo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBImg))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jData, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jData1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLData1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jBImg)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBBusca)
                        .addComponent(jBPdf))
                    .addComponent(jBTudo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(jPGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscaActionPerformed
        String dt1, dt2 = "";
        if(jCTipo.getSelectedItem().equals("Dia")){
            if(jData.getDate() != null){
                dt1 = new SimpleDateFormat("yyyy/MM/dd").format(jData.getDate());
                modelo.Search(dt1,dt1);
                geraGrafico(dt1, dt1);
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
                geraGrafico(dt1, dt2);
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
        int img = JOptionPane.showConfirmDialog(this, "Gostaria de anexar o Gráfico ao PDF ?","Anexo", JOptionPane.YES_NO_OPTION);
        GerarPDF pdf = new GerarPDF();
        String userhome = System.getProperty("user.home");
        JFileChooser Arquivo = new JFileChooser(userhome + "\\Desktop");
        Arquivo.setFileFilter(new FileNameExtensionFilter("Arquivos PDF", "pdf"));
        Arquivo.setAcceptAllFileFilterUsed(false);
        int rs = Arquivo.showSaveDialog(this);
        if(jCTipo.getSelectedItem().equals("Dia") && jTRelatorio.getSelectedRow() != -1){
            if(rs != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(this, "Erro ao Salvar!","Atenção",JOptionPane.ERROR_MESSAGE);
            }else{
                if(img == JOptionPane.YES_OPTION){
                    try{
                        ChartUtilities.saveChartAsJPEG(new java.io.File(userhome + "\\Desktop\\Gráfico.jpg"), grafico, 700, 600);
                    }catch(IOException e){
                        logger.error(e);
                        JOptionPane.showMessageDialog(this,"Erro na criação do arquivo!","Atenção",JOptionPane.ERROR_MESSAGE);
                    }
                    String dt = String.valueOf(modelo.PegaDadosLinha(jTRelatorio.getSelectedRow()).getData());
                    String[] dtstr = dt.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt, true);
                }else{
                    String dt = String.valueOf(modelo.PegaDadosLinha(jTRelatorio.getSelectedRow()).getData());
                    String[] dtstr = dt.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt, false);
                }
            }
        }else{
            if(rs != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(this, "Erro ao Salvar!","Atenção",JOptionPane.ERROR_MESSAGE);
            }else{
                if(img == JOptionPane.YES_OPTION){
                    try{
                        ChartUtilities.saveChartAsJPEG(new java.io.File(userhome + "\\Desktop\\Gráfico.jpg"), grafico, 700, 600);
                    }catch(IOException e){
                        logger.error(e);
                        JOptionPane.showMessageDialog(this,"Erro na criação do arquivo!","Atenção",JOptionPane.ERROR_MESSAGE);
                    }
                    int row = jTRelatorio.getRowCount();
                    String dt = String.valueOf(jTRelatorio.getValueAt(0, 0));
                    String dt2 = String.valueOf(jTRelatorio.getValueAt(row-1, 0));
                    String[] dtstr = dt.split("/");
                    String[] dtstr2 = dt2.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    LocalDate ndt2 = LocalDate.of(Integer.parseInt(dtstr2[2]), Integer.parseInt(dtstr2[1]), Integer.parseInt(dtstr2[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt2, true);
                }else{
                    int row = jTRelatorio.getRowCount();
                    String dt = String.valueOf(jTRelatorio.getValueAt(0, 0));
                    String dt2 = String.valueOf(jTRelatorio.getValueAt(row-1, 0));
                    String[] dtstr = dt.split("/");
                    String[] dtstr2 = dt2.split("/");
                    LocalDate ndt = LocalDate.of(Integer.parseInt(dtstr[2]), Integer.parseInt(dtstr[1]), Integer.parseInt(dtstr[0]));
                    LocalDate ndt2 = LocalDate.of(Integer.parseInt(dtstr2[2]), Integer.parseInt(dtstr2[1]), Integer.parseInt(dtstr2[0]));
                    pdf.setLocal(String.valueOf(Arquivo.getSelectedFile()));
                    pdf.GerarPDF(ndt, ndt2, false);
                }
            }
        }
    }//GEN-LAST:event_jBPdfActionPerformed

    private void jBImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImgActionPerformed
        String userhome = System.getProperty("user.home");
        JFileChooser Arquivo = new JFileChooser(userhome + "\\Desktop");
        Arquivo.setFileFilter(new FileNameExtensionFilter("Arquivos de Imagem", "jpg"));
        Arquivo.setAcceptAllFileFilterUsed(false);
        int rs = Arquivo.showSaveDialog(this);
        if(rs != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(this,"Erro ao Salvar!","Atenção",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                ChartUtilities.saveChartAsJPEG(new java.io.File(Arquivo.getSelectedFile() + ".jpg"), grafico, 700, 600);
            }catch(IOException e){
                JOptionPane.showMessageDialog(this,"Erro na criação do arquivo!","Atenção",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBImgActionPerformed

    private void jTRelatorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTRelatorioMouseClicked
        if(jCTipo.getSelectedItem().equals("Dia")){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            jData.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(modelo.getValueAt(jTRelatorio.getSelectedRow(), 0))));
            geraGrafico(sdf.format(jData.getDate()), sdf.format(jData.getDate()));
        } catch (ParseException ex) {
            logger.error(ex);
        }
        }
    }//GEN-LAST:event_jTRelatorioMouseClicked

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
    private javax.swing.JButton jBImg;
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
