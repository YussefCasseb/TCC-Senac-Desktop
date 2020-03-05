package Objetos;

public class Vendas {
    
    private String data, produto;
    private int qtde_vendida;
    
    public String getproduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQtde_vendida() {
        return qtde_vendida;
    }

    public void setQtde_vendida(int qtde_vendida) {
        this.qtde_vendida = qtde_vendida;
    }
    
}
