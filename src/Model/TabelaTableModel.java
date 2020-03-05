package Model;

import DAO.RelatorioDAO;
import Objetos.Relatorio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaTableModel extends AbstractTableModel{
    
    private final String[] colunas = {"Data","Faturamento","Qtde_Vendido"};
    private final List<Relatorio> dados = new ArrayList<>();
    
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
                return dados.get(linha).getFaturamento();
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
                dados.get(linha).setFaturamento(Double.parseDouble((String) valor));
                break;
            case 2:
                dados.get(linha).setQtde_vendida(Integer.parseInt((String) valor));
                break;
        }
    }
    
    public void addLinha(Relatorio r) {        
        this.dados.add(r);
        this.fireTableDataChanged();
    }
    
    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public Relatorio PegaDadosLinha(int linha){
        return dados.get(linha);
    }
    
    public void LerDados(){
        
        RelatorioDAO rdao = new RelatorioDAO();
        
        for (Relatorio r: rdao.read()) {
            this.addLinha(r);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void SearchSoma(String valor, String valor2){
        
        this.dados.clear();
        RelatorioDAO rdao = new RelatorioDAO();
        
        for (Relatorio r: rdao.Search(valor, valor2)) {
            this.addLinha(r);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void Search(String valor, String valor2){
        
        this.dados.clear();
        RelatorioDAO rdao = new RelatorioDAO();
        
        for (Relatorio r: rdao.Search(valor, valor2)) {
            this.addLinha(r);
        }
        
        this.fireTableDataChanged();
        
    }
    
    public void RecarregaTabela(){
        this.dados.clear();
        LerDados();
        this.fireTableDataChanged();
    }
    
    public List<Relatorio> RetornaArray(){
        return dados;
    }
    
}
