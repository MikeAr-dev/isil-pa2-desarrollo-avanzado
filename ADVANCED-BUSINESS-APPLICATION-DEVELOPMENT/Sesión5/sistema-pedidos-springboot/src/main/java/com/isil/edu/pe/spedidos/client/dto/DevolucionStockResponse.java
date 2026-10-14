package com.isil.edu.pe.spedidos.client.dto;

public class DevolucionStockResponse {
    private String codigo;
    private int cantidadDevuelta;
    private int stockRestante;

    public String getCodigo() { return codigo; }
    public int getCantidadDevuelta() { return cantidadDevuelta; }
    public int getStockRestante() { return stockRestante; }
}
