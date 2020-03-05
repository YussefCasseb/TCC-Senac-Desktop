package Model;

import DAO.VendasDAO;
import Objetos.Vendas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VendaTableModel extends AbstractTableModel{
    
    private final String[] colunas = {"Data","Produto","Qtde_Vendido"};
    private final List<Vendas> dados = new ArrayList<>();
    
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
                return dados.get(linha).getData();
            case 1:
                return dados.get(linha).getproduto();
            case 2:
                return dados.get(linha).getQtde_vendida();
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch(coluna){
            case 0:
                dados.get(linha).setData((String) valor);
                break;
            case 1:
                dados.get(linha).setProduto((String) valor);
                break;
            case 2:
                dados.get(linha).setQtde_vendida(Integer.parseInt((String) valor));
                break;
        }
    }
    
    public void addLinha(Vendas v) {        
        this.dados.add(v);
        this.fireTableDataChanged();
    }
    
    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public Vendas PegaDadosLinha(int linha){
        return dados.get(linha);
    }
    
    public void LerDados(){
        
        VendasDAO vdao = new VendasDAO();
        
        for (Vendas v: vdao.read()) {
            this.addLinha(v);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void Search(String valor, String valor2){
        
        this.dados.clear();
        VendasDAO vdao = new VendasDAO();
        
        for (Vendas v: vdao.Search(valor, valor2)) {
            this.addLinha(v);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void RecarregaTabela(){
        this.dados.clear();
        LerDados();
        this.fireTableDataChanged();
    }
    
    public List<Vendas> RetornaArray(){
        return dados;
    }
    
}
