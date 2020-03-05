package projetointegrador;

import Interfaces.Login;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ProjetoIntegrador {

    public static void main(String[] args){
        String userhome = System.getProperty("user.home");
        PropertyConfigurator.configure(userhome + "\\Desktop\\ProjetoIntegrador\\log4j.properties");
        Logger logger = Logger.getLogger("file");
	logger.info("Iniciando a aplicação");
        Login log = new Login();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }
    
}
