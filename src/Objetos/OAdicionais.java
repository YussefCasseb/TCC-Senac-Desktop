package Objetos;

public class OAdicionais {
    
    private String adicional, prod, cprod;
    private double valor;
    private int cod_adic;
    
    public int getCod_adic() {
        return cod_adic;
    }

    public void setCod_adic(int cod_adic) {
        this.cod_adic = cod_adic;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }
    
    public String getCProd() {
        return cprod;
    }

    public void setCProd(String cprod) {
        this.cprod = cprod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
