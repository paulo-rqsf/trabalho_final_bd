package br.com.compasso.models;

public class ShowOrder
{
    private int prazo;
    private double valorFrete;
    private double valorTotal;

    public ShowOrder(int prazo, double valorFrete, double valorTotal) {
        this.prazo = prazo;
        this.valorFrete = valorFrete;
        this.valorTotal = valorTotal;
    }
}
