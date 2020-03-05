package Conexão;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class ConexaoSQL {
    
    static Logger logger = Logger.getLogger("file");
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gypsy";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new RuntimeException("Erro na Conexão: " +e);
        }
    
    }
    
    public static void CloseConnection(Connection con){
    
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        
    }
    
    public static void CloseConnection(Connection con, PreparedStatement stmt){
    
        CloseConnection(con);
        
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        
    }
    
    public static void CloseConnection(Connection con, PreparedStatement stmt, ResultSet rs){
    
        CloseConnection(con, stmt);
        
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        
    }
    
}
