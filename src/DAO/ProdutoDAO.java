package DAO;

import Conexão.ConexaoSQL;
import Objetos.OProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class ProdutoDAO {
    
    public static String alert = "";
    static Logger logger = Logger.getLogger("file");
    
    public List<OProduto> pegaProd(String tipo) {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OProduto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos WHERE Tipo LIKE ?");
            stmt.setString(1, "%"+tipo+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OProduto p = new OProduto();
                p.setCod_prod(rs.getInt("Cod_Prod"));
                p.setNome(rs.getString("Nome"));
                p.setValor(rs.getDouble("Valor"));
                p.setAdic(rs.getBoolean("Adicionais"));
                produtos.add(p);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD!","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return produtos;    
    }
    
    public void Create(OProduto p){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_produto (Nome,Tipo,Valor,Adicionais) VALUES (?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, 0);
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
    
    public List<OProduto> read() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OProduto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OProduto p = new OProduto();
                p.setCod_prod(rs.getInt("Cod_Prod"));
                p.setNome(rs.getString("Nome"));
                p.setValor(rs.getDouble("Valor"));
                p.setTipo(rs.getString("Tipo"));
                p.setAdic(rs.getBoolean("Adicionais"));
                produtos.add(p);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return produtos;    
    }
    
    public void Update(OProduto p){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbl_produto SET Nome = ?, Tipo = ?, Valor = ? WHERE Cod_Prod = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getCod_prod());
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
    
    public void Delete(int id){
    
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_produto WHERE Cod_Prod = ?");
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
    
    public List<OProduto> Search(String valor){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OProduto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_produtos WHERE Nome LIKE ?");
            stmt.setString(1, "%"+valor+"%");
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    OProduto p = new OProduto();
                    p.setCod_prod(rs.getInt("Cod_Prod"));
                    p.setNome(rs.getString("Nome"));
                    p.setValor(rs.getDouble("Valor"));
                    p.setTipo(rs.getString("Tipo"));
                    p.setAdic(rs.getBoolean("Adicionais"));
                    produtos.add(p);
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
        
        return produtos;
    }
    
}
