package DAO;

import Conexão.ConexaoSQL;
import Objetos.Vendas;
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

public class VendasDAO {
    
    public static Date dt = null;
    static Logger logger = Logger.getLogger("file");
    
    public List<Vendas> read() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendas> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT prod.Nome, itens.Data, SUM(itens.Qtde) AS Qtd FROM vw_itens_pedido itens INNER JOIN vw_produtos prod ON itens.Cod_Prod = Prod.Cod_Prod GROUP BY itens.Cod_Prod");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas v = new Vendas();
                v.setProduto(rs.getString("Nome"));
                dt = rs.getDate("Data");
                Date data = rs.getDate("Data");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                v.setData(sdf.format(data));
                v.setQtde_vendida(rs.getInt("Qtd"));
                vendas.add(v);
            }
            
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return vendas;    
    }
    
    public List<Vendas> Search(String valor, String valor2){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendas> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT prod.Nome, itens.Data, SUM(itens.Qtde) AS Qtd FROM vw_itens_pedido itens INNER JOIN vw_produtos prod ON itens.Cod_Prod = Prod.Cod_Prod WHERE itens.Data BETWEEN ? AND ? GROUP BY itens.Cod_Prod");
            stmt.setString(1, valor);
            stmt.setString(2, valor2);
            rs = stmt.executeQuery();
              if(rs.next()){
                  do{                      
                    Vendas v = new Vendas();
                    v.setProduto(rs.getString("Nome"));
                    Date data = rs.getDate("Data");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    v.setData(sdf.format(data));
                    v.setQtde_vendida(Integer.parseInt(rs.getString("Qtd")));
                    vendas.add(v);
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
        
        return vendas;
    }
    
    
}
