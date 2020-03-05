package DAO;

import Conexão.ConexaoSQL;
import Objetos.OAdicionais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class AdicionaisDAO {
    
    public static String alert = "";
    static Logger logger = Logger.getLogger("file");
    
    public List<OAdicionais> pegaProd() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OAdicionais> adicionais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OAdicionais a = new OAdicionais();
                a.setCProd(rs.getString("Nome"));
                adicionais.add(a);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return adicionais;    
    }
    
    public void Create(OAdicionais a){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_adicionais (Adic_Nome,Produto,Adic_Valor) VALUES (?,?,?)");
            stmt.setString(1, a.getAdicional());
            stmt.setString(2, a.getProd());
            stmt.setDouble(3, a.getValor());
            if (stmt.execute()) {
                this.alert = "Falha ao Inserir!";
            } else {
                this.alert = "Inserido com Sucesso!";
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao registrar informações: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
    }
    
    public List<OAdicionais> read() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OAdicionais> adicionais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_adicionais WHERE Cod_Adic > 1");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OAdicionais a = new OAdicionais();
                a.setCod_adic(rs.getInt("Cod_Adic"));
                a.setAdicional(rs.getString("Adic_Nome"));
                a.setProd(rs.getString("Produto"));
                a.setValor(rs.getDouble("Adic_Valor"));
                adicionais.add(a);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return adicionais;    
    }
    
    public void Update(OAdicionais a){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbl_adicionais SET Adic_Nome = ?, Produto = ?, Adic_Valor = ? WHERE Cod_Adic = ?");
            stmt.setString(1, a.getAdicional());
            stmt.setString(2, a.getProd());
            stmt.setDouble(3, a.getValor());
            stmt.setInt(4, a.getCod_adic());
            if (stmt.execute()) {
                this.alert = "Falha ao Atualizar!";
            } else {
                this.alert = "Atualizado com Sucesso!";
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public void UpdateProdAdic(String prod){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbl_produto SET Adicionais = ? WHERE Nome = ?");
            stmt.setBoolean(1, true);
            stmt.setString(2, prod);
            stmt.execute();
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public void Delete(int id){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_adicionais WHERE Cod_Adic = ?");
            stmt.setInt(1, id);
            if (stmt.execute()) {
                this.alert = "Falha ao Excluir!";
            } else {
                this.alert = "Excluido com Sucesso!";
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao deletar o registro: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public void DeleteProdAdic(String prod){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbl_produto SET Adicionais = ? WHERE Nome = ?");
            stmt.setBoolean(1, false);
            stmt.setString(2, prod);
            stmt.execute();
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
        
    }
    
    public List<OAdicionais> Search(String valor){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OAdicionais> adicionais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_adicionais WHERE Produto LIKE ?");
            stmt.setString(1, "%"+valor+"%");
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    OAdicionais a = new OAdicionais();
                    a.setCod_adic(rs.getInt("Cod_Adic"));
                    a.setAdicional(rs.getString("Nome"));
                    a.setProd(rs.getString("Produto"));
                    a.setValor(rs.getDouble("Valor"));
                    adicionais.add(a);
                  }while (rs.next());
              }else{
                JOptionPane.showMessageDialog(null, "Cadastro Não Encontrado","Atenção",JOptionPane.ERROR_MESSAGE);
              }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return adicionais;
    }
    
}
