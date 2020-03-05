package Objetos;

public class OProduto {
    
    private String nome, tipo;
    private int cod_prod;
    private double valor;
    private boolean adic;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the adic
     */
    public boolean isAdic() {
        return adic;
    }

    /**
     * @param adic the adic to set
     */
    public void setAdic(boolean adic) {
        this.adic = adic;
    }
        
}
