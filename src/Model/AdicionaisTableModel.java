package Model;

import DAO.AdicionaisDAO;
import Objetos.OAdicionais;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AdicionaisTableModel extends AbstractTableModel{

    private final String[] colunas = {"Produto","Adicional","Valor"};
    private final List<OAdicionais> dados = new ArrayList<>();
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getProd();
            case 1:
                return dados.get(linha).getAdicional();
            case 2:
                return dados.get(linha).getValor();
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch(coluna){
            case 0:
                dados.get(linha).setProd((String) valor);
                break;
            case 1:
                dados.get(linha).setAdicional((String) valor);
                break;
            case 2:
                dados.get(linha).setValor(Double.parseDouble((String) valor));
                break;
        }
    }
    
    public void addLinha(OAdicionais a) {        
        this.dados.add(a);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public OAdicionais PegaDadosLinha(int linha){
        return dados.get(linha);
    }
    
    public void LerDados(){
        
        AdicionaisDAO adao = new AdicionaisDAO();
        
        for (OAdicionais a: adao.read()) {
            this.addLinha(a);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void Search(String valor){
        
        this.dados.clear();
        AdicionaisDAO adao = new AdicionaisDAO();
        
        for (OAdicionais a: adao.Search(valor)) {
            this.addLinha(a);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void RecarregaTabela(){
        this.dados.clear();
        LerDados();
        this.fireTableDataChanged();
    }
    
    public List<OAdicionais> RetornaArray(){
        return dados;
    }
    
}
