package DAO;

import Conexão.ConexaoSQL;
import Objetos.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class MesaDAO {
    
    public static String att = "";
    public static String alert = "";
    static Logger logger = Logger.getLogger("file");
    
    public List<Mesa> pegaAdicionais(String prod) {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mesa> mesa = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_adicionais WHERE Produto = ?");
            stmt.setString(1, prod);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Mesa m = new Mesa();
                m.setAdic(rs.getString("Adic_Nome"));
                mesa.add(m);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return mesa;    
    }
    
    public void Create(Mesa m){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("CALL sp_pedido (?,?,?,?)");
            stmt.setString(1, m.getLocali());
            stmt.setInt(2, m.getComanda());
            stmt.setDouble(3, m.getVtotal());
            stmt.setString(4, m.getObserv());
            rs = stmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("Att").equals("Atualizada")){
                    JOptionPane.showMessageDialog(null, "Mesa Atualizada!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Mesa Aberta!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    att = rs.getString("Att");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao Abrir a Mesa!","Atenção",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao registrar informações!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
    }
    
    public void CreateItens(Mesa m, String locali, int comanda){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("CALL sp_itens (?,?,?,?)");
            stmt.setInt(1, m.getCod_prod());
            stmt.setInt(2, comanda);
            stmt.setInt(3, m.getCod_adic());
            stmt.setString(4, locali);
            if (stmt.execute()) {
                this.alert = "Falha ao Registrar Pedidos!";
            } else {
                this.alert = "Pedidos Registrados com Sucesso!";
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao registrar informações!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
    }
    
    public List<Mesa> Search(String valor){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mesa> mesa = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos WHERE Nome = ?");
            stmt.setString(1, valor);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    Mesa m = new Mesa();
                    m.setCod_prod(rs.getInt("Cod_Prod"));
                    m.setProduto(rs.getString("Nome"));
                    m.setVproduto(rs.getDouble("Valor"));
                    mesa.add(m);
                  }while (rs.next());
              }else{
                JOptionPane.showMessageDialog(null, "Cadastro Não Encontrado","Atenção",JOptionPane.ERROR_MESSAGE);
              }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return mesa;
    }
    
    public List<Mesa> SearchVAdic(String nprod, String nadic){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mesa> mesa = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos INNER JOIN vw_adicionais ON vw_produtos.Nome = vw_adicionais.Produto WHERE vw_produtos.Nome = ? AND vw_adicionais.Adic_Nome = ?");
            stmt.setString(1, nprod);
            stmt.setString(2, nadic);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    Mesa m = new Mesa();
                    m.setCod_prod(rs.getInt("Cod_Prod"));
                    m.setCod_adic(rs.getInt("Cod_Adic"));
                    m.setProduto(rs.getString("Nome"));
                    m.setVproduto(rs.getDouble("Valor") + rs.getDouble("Adic_Valor"));
                    mesa.add(m);
                  }while (rs.next());
              }else{
                JOptionPane.showMessageDialog(null, "Cadastro Não Encontrado","Atenção",JOptionPane.ERROR_MESSAGE);
              }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return mesa;
    }
    
    public List<Mesa> SearchMesaAdic(int valor){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mesa> mesa = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("CALL sp_pegar_itens_adic (?)");
            stmt.setInt(1, valor);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{
                    for(int i = 0; i < rs.getInt("Qtde"); i++){
                        Mesa m = new Mesa();
                        m.setCod_prod(rs.getInt("Cod_Prod"));
                        m.setProduto(rs.getString("Nome"));
                        m.setAdic(rs.getString("Adic_Nome"));
                        m.setVproduto(rs.getDouble("Valor") + rs.getDouble("Adic_Valor"));
                        mesa.add(m);
                    }
                  }while (rs.next());
              }else{
                JOptionPane.showMessageDialog(null, "Cadastro Não Encontrado","Atenção",JOptionPane.ERROR_MESSAGE);
              }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return mesa;
    }
    
    public void SearchObserv(int comanda){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_pedido WHERE Num_Comanda = ? AND Status = ?");
            stmt.setInt(1, comanda);
            stmt.setInt(2, 1);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Mesa m = new Mesa();
                m.setObserv(rs.getString("Observacao"));
                m.setLocali(rs.getString("Localidade"));
                m.setPendente(rs.getDouble("Valor_Total"));
                m.setPago(rs.getDouble("Valor_Pago"));
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
    }
    
    public void Fechar(int comanda){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("CALL sp_fechar (?)");
            stmt.setInt(1, comanda);
            if (stmt.execute()) {
                JOptionPane.showMessageDialog(null, "Falha ao Fechar a Mesa!","Atenção",JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Mesa Fechada com Sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao deletar o registro!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public void Pago(int comanda, double pago){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbl_pedido SET Valor_Total = Valor_Total - ?, Valor_Pago = Valor_Pago + ? WHERE Num_Comanda = ? AND Status = ?");
            stmt.setDouble(1, pago);
            stmt.setDouble(2, pago);
            stmt.setInt(3, comanda);
            stmt.setInt(4, 1);
            if (stmt.execute()) {
                JOptionPane.showMessageDialog(null, "Falha ao Fechar a Mesa!","Atenção",JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Mesa Fechada com Sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao deletar o registro!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public void ExcluirPedido(int prod,int comanda){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("CALL sp_itens_exc (?,?)");
            stmt.setInt(1, prod);
            stmt.setInt(2, comanda);
            if (stmt.execute()) {
                this.alert = "Falha ao Excluir!";
            } else {
                this.alert = "Excluido com Sucesso!";
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao deletar o registro!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
}
