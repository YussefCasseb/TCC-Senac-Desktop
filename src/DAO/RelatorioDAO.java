package DAO;

import Conexão.ConexaoSQL;
import Objetos.Relatorio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class RelatorioDAO {
    
    public static Date dt = null;
    static Logger logger = Logger.getLogger("file");
    
    public List<Relatorio> read() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Relatorio> relatorio = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_relatorio_vendas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Relatorio r = new Relatorio();
                r.setCodigo(rs.getInt("Codigo"));
                dt = rs.getDate("Data");
                Date data = rs.getDate("Data");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                r.setData(sdf.format(data));
                r.setFaturamento(rs.getDouble("Faturamento"));
                r.setQtde_vendida(rs.getInt("Qtde_Vendido"));
                relatorio.add(r);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return relatorio;    
    }
    
    public List<Relatorio> SearchSoma(String valor, String valor2){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Relatorio> relatorio = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT Codigo, Data, SUM(Faturamento) AS Valort, SUM(Qtde_Vendido) AS Qtde FROM vw_relatorio_vendas WHERE Data BETWEEN ? AND ?");
            stmt.setString(1, valor);
            stmt.setString(2, valor2);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    Relatorio r = new Relatorio();
                    r.setCodigo(rs.getInt("Codigo"));
                    Date data = rs.getDate("Data");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    r.setData(sdf.format(data));
                    r.setFaturamento(Double.parseDouble(rs.getString("Valort")));
                    r.setQtde_vendida(Integer.parseInt(rs.getString("Qtde")));
                    relatorio.add(r);
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
        
        return relatorio;
    }
    
    public List<Relatorio> Search(String valor, String valor2){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Relatorio> relatorio = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT Codigo, Data, Faturamento AS Valort, Qtde_Vendido AS Qtde FROM vw_relatorio_vendas WHERE Data BETWEEN ? AND ?");
            stmt.setString(1, valor);
            stmt.setString(2, valor2);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    Relatorio r = new Relatorio();
                    r.setCodigo(rs.getInt("Codigo"));
                    Date data = rs.getDate("Data");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    r.setData(sdf.format(data));
                    r.setFaturamento(Double.parseDouble(rs.getString("Valort")));
                    r.setQtde_vendida(Integer.parseInt(rs.getString("Qtde")));
                    relatorio.add(r);
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
        
        return relatorio;
    }
    
    
}
