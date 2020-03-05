package Objetos;

public class Relatorio {
    
    private String data;
    private double faturamento;
    private int codigo,qtde_vendida;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public int getQtde_vendida() {
        return qtde_vendida;
    }

    public void setQtde_vendida(int qtde_vendida) {
        this.qtde_vendida = qtde_vendida;
    }
    
}
