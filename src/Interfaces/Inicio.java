package Interfaces;

import DAO.RelatorioDAO;
import Objetos.OLogin;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Inicio extends javax.swing.JFrame {
    
    static Logger logger = Logger.getLogger("file");
    private BufferedImage imagem;
    
    public Inicio() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Erro: " +e);
	}
        
        String userhome = System.getProperty("user.home");
        
        try {
            this.setIconImage(ImageIO.read(new File(userhome + "\\Desktop\\ProjetoIntegrador\\Icone32.png")));
        } catch (IOException ex) {
            logger.error(ex);
        }
        
        initComponents();
        
        OLogin lg = new OLogin();
        
        if(lg.getAccess() != 1){
            jBProds.setVisible(false);
            jBAdics.setVisible(false);
            jBRels.setVisible(false);
            jBVendas.setVisible(false);
            ModificarMesas mesa = new ModificarMesas();
            mesa.setClosable(false);
            jDesktopPane.add(mesa);
            try {
                mesa.setMaximum(true);
            } catch (PropertyVetoException ex) {
                logger.error(ex);
            }
            mesa.show();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        String userhome = System.getProperty("user.home");
        try {
            imagem = ImageIO.read(new File(userhome + "\\Desktop\\ProjetoIntegrador\\fpreto2.jpg"));
        } catch (Exception e) {
            logger.error(e);
        }
        jDesktopPane = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), this);
            }

        };
        jBProds = new javax.swing.JButton();
        jBAdics = new javax.swing.JButton();
        jBRels = new javax.swing.JButton();
        jBVendas = new javax.swing.JButton();
        jBPedidos = new javax.swing.JButton();
        jBSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CC - Comercial Controller");

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jBProds.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBProds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/box.png"))); // NOI18N
        jBProds.setText("Produtos");
        jBProds.setBorderPainted(false);
        jBProds.setContentAreaFilled(false);
        jBProds.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBProds.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBProds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProdsActionPerformed(evt);
            }
        });

        jBAdics.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBAdics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionais.png"))); // NOI18N
        jBAdics.setText("Adicionais");
        jBAdics.setBorderPainted(false);
        jBAdics.setContentAreaFilled(false);
        jBAdics.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAdics.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAdics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicsActionPerformed(evt);
            }
        });

        jBRels.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBRels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/relatorio.png"))); // NOI18N
        jBRels.setText("Relatório");
        jBRels.setBorderPainted(false);
        jBRels.setContentAreaFilled(false);
        jBRels.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBRels.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBRels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRelsActionPerformed(evt);
            }
        });

        jBVendas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/vendas.png"))); // NOI18N
        jBVendas.setText("Vendas");
        jBVendas.setBorderPainted(false);
        jBVendas.setContentAreaFilled(false);
        jBVendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBVendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVendasActionPerformed(evt);
            }
        });

        jBPedidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pedidos.png"))); // NOI18N
        jBPedidos.setText("Pedidos");
        jBPedidos.setBorderPainted(false);
        jBPedidos.setContentAreaFilled(false);
        jBPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBPedidos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPedidosActionPerformed(evt);
            }
        });

        jBSair.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit-icon.png"))); // NOI18N
        jBSair.setText("Sair");
        jBSair.setBorderPainted(false);
        jBSair.setContentAreaFilled(false);
        jBSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBProds)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAdics)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBRels)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBProds)
                    .addComponent(jBAdics)
                    .addComponent(jBRels)
                    .addComponent(jBVendas)
                    .addComponent(jBPedidos)
                    .addComponent(jBSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBProdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProdsActionPerformed
        Produto prod = new Produto();
        jDesktopPane.add(prod);
        prod.setSize(435, 642);
        prod.show();
        Dimension d = jDesktopPane.getSize();
        prod.setLocation((d.width - prod.getSize().width) / 2, (d.height - prod.getSize().height) / 2);
    }//GEN-LAST:event_jBProdsActionPerformed

    private void jBAdicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicsActionPerformed
        Adicionais adic = new Adicionais();
        jDesktopPane.add(adic);
        adic.setSize(435, 642);
        adic.show();
        Dimension d = jDesktopPane.getSize();
        adic.setLocation((d.width - adic.getSize().width) / 2, (d.height - adic.getSize().height) / 2);
    }//GEN-LAST:event_jBAdicsActionPerformed

    private void jBRelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRelsActionPerformed
        RelatorioDAO rdao = new RelatorioDAO();
        rdao.read();
        if(RelatorioDAO.dt != null){
            RTabela rt = new RTabela(new SimpleDateFormat("yyyy/MM/dd").format(RelatorioDAO.dt), new SimpleDateFormat("yyyy/MM/dd").format(RelatorioDAO.dt));
            jDesktopPane.add(rt);
            rt.setSize(1200, 530);
            rt.show();
            Dimension d = jDesktopPane.getSize();
            rt.setLocation((d.width - rt.getSize().width) / 2, (d.height - rt.getSize().height) / 2);
        }else{
            JOptionPane.showMessageDialog(this, "Ainda não existem Relatórios para serem Exibidos!");
        }
    }//GEN-LAST:event_jBRelsActionPerformed

    private void jBVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVendasActionPerformed
        Vendas rt = new Vendas();
        jDesktopPane.add(rt);
        rt.setSize(650, 530);
        rt.show();
        Dimension d = jDesktopPane.getSize();
        rt.setLocation((d.width - rt.getSize().width) / 2, (d.height - rt.getSize().height) / 2);
    }//GEN-LAST:event_jBVendasActionPerformed

    private void jBPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPedidosActionPerformed
        ModificarMesas mesa = new ModificarMesas();
        jDesktopPane.add(mesa);
        mesa.setSize(jDesktopPane.getWidth(), jDesktopPane.getHeight());
        mesa.show();
    }//GEN-LAST:event_jBPedidosActionPerformed

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        this.dispose();
        Login log = new Login();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }//GEN-LAST:event_jBSairActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAdics;
    private javax.swing.JButton jBPedidos;
    private javax.swing.JButton jBProds;
    private javax.swing.JButton jBRels;
    private javax.swing.JButton jBSair;
    private javax.swing.JButton jBVendas;
    public javax.swing.JDesktopPane jDesktopPane;
    // End of variables declaration//GEN-END:variables
}
