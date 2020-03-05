package DAO;

import Conexão.ConexaoSQL;
import Objetos.OLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class LoginDAO {
    
    static Logger logger = Logger.getLogger("file");
    
    public boolean Verifica(String user, String pass){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_login WHERE User = ? AND Pass = ?");
            stmt.setString(1, user);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if(rs.next()){
                OLogin lg = new OLogin();
                lg.setAccess(rs.getInt("Access"));
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: ","Atenção",JOptionPane.ERROR_MESSAGE);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return false;
    }
    
}
