package Interfaces;

import DAO.MesaDAO;
import DAO.ProdutoDAO;
import Model.MesaTableModel;
import Objetos.Mesa;
import Objetos.OLogin;
import Objetos.OProduto;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ModificarMesas extends javax.swing.JInternalFrame {

    ProdutoDAO pdao = new ProdutoDAO();
    MesaDAO mdao = new MesaDAO();
    Mesa m = new Mesa();
    public static MesaTableModel modelo = new MesaTableModel();
    public static double valor = 0;
    public static String prod;
    public static int comanda;
    
    
    public ModificarMesas() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Erro: " +e);
	}
        
        initComponents();
        
        modelo.RecarregaTabela();
        
        OLogin lg = new OLogin();
        
        if(lg.getAccess() != 1){
            jBExc.setVisible(false);
        }
        
        jTPedido.setModel(modelo);
        
        if(MesaDAO.alert.equals("Falha ao Registrar Pedidos!") || MesaDAO.alert.equals("Falha ao Excluir!")){
            jLAlert.setForeground(Color.red);
            jLAlert.setText(MesaDAO.alert);
            MesaDAO.alert = "";
        }else{
            jLAlert.setText(MesaDAO.alert);
            MesaDAO.alert = "";
        }
        
    }
    
    public final void attProdutos(String tipo){
        jPProdutos.removeAll();
        jPProdutos.revalidate();
        jPProdutos.repaint();
        jPProdutos.setLayout(new  GridLayout(4,4));
        for (OProduto p: pdao.pegaProd(tipo)) {			
            String name = p.getNome();
            Button b = new Button(name);
            b.setActionCommand(name);
            b.setFont(new Font("Arial", Font.BOLD, 18));
            b.addActionListener( new ActionListener() {				
                public void actionPerformed(ActionEvent e) {
                    for (Mesa m : mdao.Search(e.getActionCommand())){
                        if(jTComanda.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Informe um Numero de Comanda!","Atenção",JOptionPane.ERROR_MESSAGE);
                        }else{
                            if(p.isAdic()){
                                prod = m.getProduto();
                                ProdAdd mesa = new ProdAdd();
                                mesa.setSize(320, 200);
                                getParent().add(mesa);
                                mesa.show();
                                Dimension d = getParent().getSize();
                                mesa.setLocation((d.width - mesa.getSize().width) / 2, (d.height - mesa.getSize().height) / 2);
                            }else{
                                valor = valor + m.getVproduto();
                                m.setCod_adic(1);
                                m.setAdic("Sem Adicionais");
                                modelo.addLinha(m);
                                jBValor.setText("R$ " + valor);
                            }
                        }
                    }
                }				
            });		
	jPProdutos.add(b);
	}
        jCTipo.revalidate();
        jCTipo.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPProdutos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPedido = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jCMesas = new javax.swing.JComboBox<>();
        jBAbrir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTComanda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jCAMesas = new javax.swing.JComboBox<>();
        jBExc = new javax.swing.JButton();
        jBFechar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTAComanda = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBValor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jBPago = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBPendente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLAlert = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTObserv = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jCTipo = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Pedidos");

        jPProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPProdutosLayout = new javax.swing.GroupLayout(jPProdutos);
        jPProdutos.setLayout(jPProdutosLayout);
        jPProdutosLayout.setHorizontalGroup(
            jPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPProdutosLayout.setVerticalGroup(
            jPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTPedido.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTPedido);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Item/Abrir Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jCMesas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Localidade", "Balcão", "Área Externa" }));

        jBAbrir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Open-folder-icon.png"))); // NOI18N
        jBAbrir.setText("Abrir/Atualizar Pedido");
        jBAbrir.setContentAreaFilled(false);
        jBAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAbrirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Numero da Comanda");

        jTComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTComandaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCMesas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jBAbrir)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAbrir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Excluir Item/Fechar Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jCAMesas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCAMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Localidade" }));

        jBExc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBExc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete-icon.png"))); // NOI18N
        jBExc.setText("Excluir Item");
        jBExc.setContentAreaFilled(false);
        jBExc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBExc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcActionPerformed(evt);
            }
        });

        jBFechar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jBFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/close-folder-icon.png"))); // NOI18N
        jBFechar.setText("Fechar Pedido");
        jBFechar.setContentAreaFilled(false);
        jBFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBFechar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFecharActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Numero da Comanda");

        jTAComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTAComandaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTAComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCAMesas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jBFechar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExc)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTAComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCAMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBFechar)
                    .addComponent(jBExc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jBValor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jBValor.setForeground(new java.awt.Color(4, 150, 0));
        jBValor.setText("R$ 0.0");
        jBValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBValor.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Pago:");

        jBPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBPago.setForeground(new java.awt.Color(4, 147, 0));
        jBPago.setText("R$ 0.0");
        jBPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBPago.setContentAreaFilled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Pendente:");

        jBPendente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBPendente.setForeground(new java.awt.Color(4, 147, 0));
        jBPendente.setText("R$ 0.0");
        jBPendente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBPendente.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jBPago)
                    .addComponent(jLabel4)
                    .addComponent(jBPendente)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jLAlert.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLAlert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLAlert.setText("Alert");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTObserv, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTObserv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escolha de Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jCTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de Produto", "Lanches", "Porções", "Bebidas" }));
        jCTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCTipo, 0, 333, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 1, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLAlert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(496, 496, 496)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLAlert)
                        .addContainerGap(71, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAbrirActionPerformed
        if(jTComanda.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe um Número de Comanda!","Atenção",JOptionPane.ERROR_MESSAGE);
        }else{
            m.setLocali(jCMesas.getSelectedItem().toString());
            m.setComanda(Integer.parseInt(jTComanda.getText()));
            m.setVtotal(valor);
            m.setObserv(jTObserv.getText());
            mdao.Create(m);
            for(Mesa m : modelo.RetornaArray()){
                mdao.CreateItens(m,jCMesas.getSelectedItem().toString(), Integer.parseInt(jTComanda.getText()));
            }            
            ModificarMesas mesa = new ModificarMesas();
            mesa.setSize(getParent().getSize());
            getParent().add(mesa);
            mesa.show();
            this.dispose();
        }
    }//GEN-LAST:event_jBAbrirActionPerformed

    private void jBExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcActionPerformed
        if(jTPedido.getSelectedRow() != -1){
            int rs = JOptionPane.showConfirmDialog(this, "Deseja excluir esse Produto ?", "Excluir " + modelo.PegaDadosLinha(jTPedido.getSelectedRow()).getProduto(), JOptionPane.YES_NO_CANCEL_OPTION);
            if(rs == JOptionPane.YES_OPTION){
                if(!jTAComanda.getText().isEmpty()){
                    mdao.ExcluirPedido(modelo.PegaDadosLinha(jTPedido.getSelectedRow()).getCod_prod(), Integer.parseInt(jTAComanda.getText()));
                    valor = valor - modelo.PegaDadosLinha(jTPedido.getSelectedRow()).getVproduto();
                    jBValor.setText("R$ " + String.valueOf(valor));
                    modelo.removeLinha(jTPedido.getSelectedRow());
                    valor = 0;
                    modelo.Search(Integer.parseInt(jTAComanda.getText()));
                    for(Mesa m : mdao.SearchMesaAdic(Integer.parseInt(jTAComanda.getText()))){
                        valor = valor + m.getVproduto();
                        jBValor.setText("R$ " + String.valueOf(valor));
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Informe um Número de Comanda!","Atenção",JOptionPane.ERROR_MESSAGE);
            }
        }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Produto!");
        }
    }//GEN-LAST:event_jBExcActionPerformed

    private void jBFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFecharActionPerformed
        if(jTAComanda.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe um Número de Comanda!","Atenção",JOptionPane.ERROR_MESSAGE);
        }else{
            FecharPedido fecha = new FecharPedido();
            fecha.setSize(180, 220);
            getParent().add(fecha);
            fecha.show();
            Dimension d = getParent().getSize();
            fecha.setLocation((d.width - fecha.getSize().width) / 2, (d.height - fecha.getSize().height) / 2);
        }
    }//GEN-LAST:event_jBFecharActionPerformed

    private void jCTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTipoActionPerformed
        if(jCTipo.getSelectedItem().equals("Lanches")){
            attProdutos(jCTipo.getSelectedItem().toString());
        }else if(jCTipo.getSelectedItem().equals("Porções")){
            attProdutos("Porcoes");
        }else if(jCTipo.getSelectedItem().equals("Bebidas")){
            attProdutos(jCTipo.getSelectedItem().toString());
        }else{
            jPProdutos.removeAll();
            jPProdutos.revalidate();
            jPProdutos.repaint();
        }
    }//GEN-LAST:event_jCTipoActionPerformed

    private void jTComandaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTComandaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jTComanda.getText().isEmpty()){
                modelo.RecarregaTabela();
                valor = 0;
                jBValor.setText("R$ " + String.valueOf(valor));
                jBPago.setText("R$ " + String.valueOf(valor));
                jBPendente.setText("R$ " + String.valueOf(valor));
                jTObserv.setText("");
                jCMesas.removeAllItems();
                jCMesas.addItem("Localidade");
            }else{
                modelo.RecarregaTabela();
                valor = 0;
                jBValor.setText("R$ " + String.valueOf(valor));
                jBPago.setText("R$ " + String.valueOf(valor));
                jBPendente.setText("R$ " + String.valueOf(valor));
                jTObserv.setText("");
                mdao.SearchObserv(Integer.parseInt(jTComanda.getText()));
                jTObserv.setText(m.getObserv());
                jCMesas.removeAllItems();
                jCMesas.addItem(m.getLocali());
            }
            MesaDAO.alert = "";
            jLAlert.setText(MesaDAO.alert);
	}
    }//GEN-LAST:event_jTComandaKeyPressed

    private void jTAComandaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAComandaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jTAComanda.getText().isEmpty()){
                modelo.RecarregaTabela();
                valor = 0;
                jBValor.setText("R$ " + String.valueOf(valor));
                jBPago.setText("R$ " + String.valueOf(valor));
                jBPendente.setText("R$ " + String.valueOf(valor));
                jTObserv.setText("");
                jCAMesas.removeAllItems();
                jCAMesas.addItem("Localidade");
            }else{
                comanda = Integer.parseInt(jTAComanda.getText());
                mdao.SearchObserv(comanda);
                jCAMesas.removeAllItems();
                jCAMesas.addItem(m.getLocali());
                jTObserv.setText(m.getObserv());
                jBPago.setText("R$ " + m.getPago());
                jBPendente.setText("R$ " + m.getPendente());                
                valor = 0;
                modelo.Search(comanda);
                    for(Mesa m : mdao.SearchMesaAdic(comanda)){
                        valor = valor + m.getVproduto();
                        jBValor.setText("R$ " + String.valueOf(valor));
                    }
            }
            MesaDAO.alert = "";
            jLAlert.setText(MesaDAO.alert);
	}
    }//GEN-LAST:event_jTAComandaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAbrir;
    private javax.swing.JButton jBExc;
    private javax.swing.JButton jBFechar;
    private javax.swing.JButton jBPago;
    private javax.swing.JButton jBPendente;
    public static javax.swing.JButton jBValor;
    private javax.swing.JComboBox<Object> jCAMesas;
    private javax.swing.JComboBox<String> jCMesas;
    private javax.swing.JComboBox<String> jCTipo;
    private javax.swing.JLabel jLAlert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTAComanda;
    private javax.swing.JTextField jTComanda;
    private javax.swing.JTextField jTObserv;
    public static javax.swing.JTable jTPedido;
    // End of variables declaration//GEN-END:variables
}
