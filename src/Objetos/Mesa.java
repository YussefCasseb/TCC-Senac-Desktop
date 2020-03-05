package Objetos;

public class Mesa {
    
    private int cod_prod,comanda, cod_adic;
    private String produto,adic;
    private double vproduto,vtotal;
    private static double pago,pendente;
    private static String observ,locali;

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getComanda() {
        return comanda;
    }

    public void setComanda(int comanda) {
        this.comanda = comanda;
    }

    public double getVproduto() {
        return vproduto;
    }

    public void setVproduto(double vproduto) {
        this.vproduto = vproduto;
    }

    public double getVtotal() {
        return vtotal;
    }

    public void setVtotal(double vtotal) {
        this.vtotal = vtotal;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        Mesa.observ = observ;
    }

    public String getLocali() {
        return locali;
    }

    public void setLocali(String locali) {
        this.locali = locali;
    }

    public String getAdic() {
        return adic;
    }

    public void setAdic(String adic) {
        this.adic = adic;
    }

    public int getCod_adic() {
        return cod_adic;
    }

    public void setCod_adic(int cod_adic) {
        this.cod_adic = cod_adic;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getPendente() {
        return pendente;
    }

    public void setPendente(double pendente) {
        this.pendente = pendente;
    }
}
