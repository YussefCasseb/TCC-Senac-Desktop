package Model;

import DAO.MesaDAO;
import Objetos.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MesaTableModel extends AbstractTableModel{
    
    private final String[] colunas = {"Produto","Adicionais","Valor"};
    private final List<Mesa> dados = new ArrayList<>();

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
                return dados.get(linha).getProduto();
            case 1:
                return dados.get(linha).getAdic();
            case 2:
                return dados.get(linha).getVproduto();
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch(coluna){
            case 0:
                dados.get(linha).setProduto((String) valor);
                break;
            case 1:
                dados.get(linha).setAdic((String) valor);
                break;
            case 2:
                dados.get(linha).setVproduto(Double.parseDouble((String) valor));
                break;
        }
    }
    
    public void addLinha(Mesa m) {        
        this.dados.add(m);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public Mesa PegaDadosLinha(int linha){
        return dados.get(linha);
    }
    
    public void Search(int valor){
        
        this.dados.clear();
        MesaDAO mdao = new MesaDAO();
        
        for (Mesa m: mdao.SearchMesaAdic(valor)) {
            this.addLinha(m);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void RecarregaTabela(){
        this.dados.clear();
        this.fireTableDataChanged();
    }
    
    public List<Mesa> RetornaArray(){
        return dados;
    }
    
}
