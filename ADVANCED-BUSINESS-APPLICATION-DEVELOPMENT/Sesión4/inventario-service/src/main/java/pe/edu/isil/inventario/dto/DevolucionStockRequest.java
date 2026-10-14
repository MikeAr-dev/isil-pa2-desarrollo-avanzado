package pe.edu.isil.inventario.dto;

import jakarta.validation.constraints.Min;

public class DevolucionStockRequest {

    @Min(value = 1, message = "La cantidad debe ser mayor que cero.")
    private int cantidad;

    public DevolucionStockRequest() {}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}